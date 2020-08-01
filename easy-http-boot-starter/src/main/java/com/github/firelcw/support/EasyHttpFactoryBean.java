package com.github.firelcw.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;

/**
 * @author 廖重威
 * @date 2020/8/1 20:17
 */
public class EasyHttpFactoryBean<T>  implements FactoryBean<T>, ApplicationContextAware {


    @Nullable
    @Override
    public T getObject() throws Exception {
        return null;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
