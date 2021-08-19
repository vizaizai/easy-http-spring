package com.github.vizaizai.boot.support;

import com.github.vizaizai.exception.EasyHttpException;
import com.github.vizaizai.interceptor.DefaultInterceptorGenerator;
import com.github.vizaizai.interceptor.HttpInterceptor;
import com.github.vizaizai.interceptor.InterceptorGenerator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author liaochongwei
 * @date 2021/7/9 17:06
 */
public class SpringInterceptorGenerator implements InterceptorGenerator,ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public HttpInterceptor get(Class<? extends HttpInterceptor> clazz) {
        Map<String, ? extends HttpInterceptor> beansOfType = applicationContext.getBeansOfType(clazz);
        if (beansOfType.isEmpty()) {
            return DefaultInterceptorGenerator.getGenerator().get(clazz);
        }
        if (beansOfType.size() > 1) {
            throw new EasyHttpException(clazz.getTypeName() + " required a single bean, but 2 were found.");
        }
        return beansOfType.entrySet().iterator().next().getValue();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
