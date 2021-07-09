package com.github.vizaizai.boot.support;

import com.github.vizaizai.interceptor.HttpInterceptor;
import com.github.vizaizai.interceptor.InterceptorGenerator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author liaochongwei
 * @date 2021/7/9 17:06
 */
public class SpringInterceptorGenerator implements InterceptorGenerator,ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public HttpInterceptor get(Class<? extends HttpInterceptor> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
