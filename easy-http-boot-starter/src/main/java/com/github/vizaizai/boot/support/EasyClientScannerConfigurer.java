package com.github.vizaizai.boot.support;

import com.github.vizaizai.boot.annotation.EasyHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.core.Ordered;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author liaochongwei
 * @date 2020/7/28 14:38
 */
public class EasyClientScannerConfigurer implements BeanDefinitionRegistryPostProcessor, Ordered {

    private static final Logger log = LoggerFactory.getLogger(EasyClientScannerConfigurer.class);
    private static String[] basePackages;
    private static final Class<? extends Annotation> markedAnnotation = EasyHttpClient.class;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry definitionRegistry) {
        EasyClientScanner scanner = new EasyClientScanner(definitionRegistry);
        // 添加扫描条件, 默认只扫描@EasyHttpClient标记的
        scanner.addIncludeFilter(new AnnotationTypeFilter(markedAnnotation));
        scanner.setBeanNameGenerator(new DefaultBeanNameGenerator());
        // 开始扫描, 并注册
        int beanCount = scanner.scan(basePackages);
        log.info("beans: [{}]", beanCount);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {

    }

    @Override
    public int getOrder() {
        return 214748364;
    }


    public static void setBasePackages(Set<String> basePackages) {
        EasyClientScannerConfigurer.basePackages =  basePackages.toArray(new String[0]);
    }
}
