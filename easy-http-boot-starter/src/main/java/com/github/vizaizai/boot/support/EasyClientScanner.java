package com.github.vizaizai.boot.support;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/** NetClientScanner
 *
 * @author liaochongwei
 * @date 2020/7/28 15:39
 */
public class EasyClientScanner extends ClassPathBeanDefinitionScanner {

    public EasyClientScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }


    /**
     * 默认情况下只有顶层具体类才会通过
     * @param beanDefinition
     * @return boolean
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface()
                && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> holders = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : holders) {
            this.convertToNetClientFactoryBean(holder.getBeanDefinition());
        }
        return holders;
    }

    /**
     * 转化为NetClientFactoryBean
     * @param beanDefinition
     */
    private void convertToNetClientFactoryBean(BeanDefinition beanDefinition) {
        // 强转
        GenericBeanDefinition mapperFactoryDefinition =
                (GenericBeanDefinition) beanDefinition;
        String mapperInterfaceName = beanDefinition.getBeanClassName();
        mapperFactoryDefinition.setBeanClass(EasyClientFactoryBean.class);
        // 使用构造函数注入
        // 这里给的只是接口的完全限定名, 而不是Class对象, 因为Spring初始化的时候
        // 会自动将字符串转化成对应的类型, 而对应这里将会使用的是ClassEditor转化功能.
        // 之所以不用Class, 是因为对应Class文件此时还没有被类加载器加载
        ConstructorArgumentValues constructorArgumentValues = mapperFactoryDefinition.getConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0, mapperInterfaceName);
    }
}
