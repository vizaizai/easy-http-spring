package com.github.firelcw.support;

import com.github.firelcw.annotation.EasyClient;
import com.github.firelcw.autoconfigure.EasyHttpProperties;
import com.github.firelcw.client.EasyHttp;
import com.github.firelcw.codec.Decoder;
import com.github.firelcw.codec.Encoder;
import com.github.firelcw.interceptor.HttpInterceptor;
import com.github.firelcw.model.HttpRequestConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author liaochongwei
 * @date 2020/7/28 14:34
 */
public class EasyClientFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private  final Class<T> interfaceType;
    private T target;
    private Encoder encoder;
    private Decoder decoder;
    private HttpRequestConfig requestConfig;
    private EasyHttpProperties properties;
    private InterceptorsBean interceptorsBean;

    public EasyClientFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
        if(interfaceType == null || !interfaceType.isInterface()) {
            throw new IllegalArgumentException("annotated type by @NetClient must be a interface.");
        }

    }
    /**
     * 创建实现类
     */
    private void createTarget() {
        EasyClient annotation = interfaceType.getAnnotation(EasyClient.class);
        String url;
        String value = annotation.value();
        // 使用默认url
        if (StringUtils.isBlank(value)) {
            url = properties.getBaseEndpoint();
            if (StringUtils.isBlank(url)) {
                throw new BeanInitializationException("the value is not exists in easy-http.baseEndpoint");
            }
        }else {
            url = properties.getBaseEndpoints().get(value);
            if (StringUtils.isBlank(url)) {
                throw new BeanInitializationException("the value '" + value +"'is not exists in easy-http.baseEndpoints");
            }
        }
        EasyHttp.Builder builder = EasyHttp.builder()
                                        .decoder(decoder)
                                        .encoder(encoder)
                                        .config(requestConfig)
                                        .url(url);
        // 拦截器
        for (HttpInterceptor interceptor : this.interceptorsBean.getInterceptors()) {
            builder.withInterceptor(interceptor);
        }

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
        this.decoder = applicationContext.getBean(Decoder.class);
        this.encoder = applicationContext.getBean(Encoder.class);
        this.requestConfig = applicationContext.getBean(HttpRequestConfig.class);
        this.properties = applicationContext.getBean(EasyHttpProperties.class);
        this.interceptorsBean = applicationContext.getBean(InterceptorsBean.class);
    }
}
