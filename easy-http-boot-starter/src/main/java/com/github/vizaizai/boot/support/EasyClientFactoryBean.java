package com.github.vizaizai.boot.support;

import com.github.vizaizai.EasyHttp;
import com.github.vizaizai.boot.annotation.EasyHttpClient;
import com.github.vizaizai.boot.autoconfigure.EasyHttpProperties;
import com.github.vizaizai.boot.autoconfigure.RetryProperties;
import com.github.vizaizai.client.AbstractClient;
import com.github.vizaizai.codec.Decoder;
import com.github.vizaizai.codec.Encoder;
import com.github.vizaizai.entity.HttpRequestConfig;
import com.github.vizaizai.hander.mapping.PathConverter;
import com.github.vizaizai.interceptor.HttpInterceptor;
import com.github.vizaizai.interceptor.InterceptorGenerator;
import com.github.vizaizai.interceptor.LogInterceptor;
import com.github.vizaizai.retry.RetryTrigger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author liaochongwei
 * @date 2020/7/28 14:34
 */
public class EasyClientFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private  final Class<T> interfaceType;
    private T target;
    private Encoder encoder;
    private Decoder decoder;
    private AbstractClient client;
    private HttpRequestConfig requestConfig;
    private EasyHttpProperties properties;
    private InterceptorsBean interceptorsBean;
    private ApplicationContext applicationContext;

    public EasyClientFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
        if(interfaceType == null || !interfaceType.isInterface()) {
            throw new IllegalArgumentException("annotated type by @EasyHttpClient must be a interface.");
        }

    }
    /**
     * 创建实现类
     */
    private void createTarget() {
        EasyHttpClient annotation = interfaceType.getAnnotation(EasyHttpClient.class);
        String url;
        String value = annotation.value();
        // 使用默认url
        if (StringUtils.isBlank(value)) {
            url = properties.getBaseEndpoint();
        }else {
            url = properties.getBaseEndpoints().get(value);
            if (StringUtils.isBlank(url)) {
                throw new BeanInitializationException("the value '" + value +"'is not exists in easy-http.baseEndpoints");
            }
        }

        EasyHttp.Builder builder = EasyHttp.builder()
                                        .encoder(encoder)
                                        .config(requestConfig)
                                        .url(url);

        // 是否开启请求日志
        if (properties.isRequestLog()) {
            builder.withInterceptor(new LogInterceptor());
        }

        // 拦截生产器
        InterceptorGenerator interceptorGenerator = this.applicationContext.getBean(InterceptorGenerator.class);
        builder.interceptorGenerator(interceptorGenerator);

        // 全局拦截器
        for (HttpInterceptor interceptor : this.interceptorsBean.getInterceptors()) {
            builder.withInterceptor(interceptor);
        }

        // 注解上的拦截器
        List<HttpInterceptor> httpInterceptors = Utils.createHttpInterceptors(annotation.interceptors(), interceptorGenerator);
        for (HttpInterceptor interceptor : httpInterceptors) {
            builder.withInterceptor(interceptor);
        }

        //注解上的客户端
        if (!annotation.client().equals(AbstractClient.class)) {
            AbstractClient annClient = this.applicationContext.getBean(annotation.client());
            Assert.notNull(annClient,"not found a bean:" + annotation.client().getSimpleName());
            builder.client(annClient);
        }else {
            builder.client(this.client);
        }

        // 注解上的解码器
        if (!annotation.decoder().equals(Decoder.class)) {
            Decoder annDecoder = this.applicationContext.getBean(annotation.decoder());
            Assert.notNull(annDecoder,"not found a bean:" + annotation.decoder().getSimpleName());
            builder.decoder(annDecoder);
        }else {
            builder.decoder(this.decoder);
        }

        // 重试
        RetryProperties retryProperties = this.properties.getRetry();
        if (retryProperties != null && retryProperties.isEnable()) {
            builder.retryable(retryProperties.getMaxAttempts(), retryProperties.getIntervalTime(),
                    this.applicationContext.getBean(RetryTrigger.class));
        }
        // 路径转化器
        PathConverter pathConverter = this.applicationContext.getBean(PathConverter.class);
        builder.pathConverter(pathConverter);

        // 构建http
        this.target = builder.build(this.interfaceType);
    }

    @Override
    public T getObject() {
        if (this.target == null) {
            this.createTarget();
        }
        return this.target;
    }

    @Override
    public Class<?> getObjectType() {
        return this.interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.decoder = applicationContext.getBean(Decoder.class);
        this.encoder = applicationContext.getBean(Encoder.class);
        this.client = applicationContext.getBean(AbstractClient.class);
        this.requestConfig = applicationContext.getBean(HttpRequestConfig.class);
        this.properties = applicationContext.getBean(EasyHttpProperties.class);
        this.interceptorsBean = applicationContext.getBean(InterceptorsBean.class);
    }
}
