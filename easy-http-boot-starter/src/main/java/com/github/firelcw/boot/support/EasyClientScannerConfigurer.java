package com.github.firelcw.boot.support;

import com.github.firelcw.boot.annotation.EasyHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * @author liaochongwei
 * @date 2020/7/28 14:38
 */
public class EasyClientScannerConfigurer implements BeanDefinitionRegistryPostProcessor, Ordered, EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(EasyClientScannerConfigurer.class);
    private String basePackage;
    private static final Class<? extends Annotation> markedAnnotation = EasyHttpClient.class;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry definitionRegistry) {
        EasyClientScanner scanner = new EasyClientScanner(definitionRegistry);
        // 添加扫描条件, 默认只扫描@NetClient标记的
        scanner.addIncludeFilter(new AnnotationTypeFilter(markedAnnotation));
        scanner.setBeanNameGenerator(new DefaultBeanNameGenerator());
        // 开始扫描, 并注册
        int beanCount = scanner.scan(StringUtils.tokenizeToStringArray(
                basePackage, ","));
        log.info("The count of registered bean is {}",beanCount);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {

    }

    @Override
    public int getOrder() {
        return 214748364;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.basePackage = environment.getProperty("easy-http.base-package");
        if (this.basePackage == null) {
            throw new IllegalArgumentException("easy-http.base-package is null");
        }
    }
}
