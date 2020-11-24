package com.github.firelcw.boot.support;

import com.github.firelcw.boot.annotation.EnableEasyHttp;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liaochongwei
 * @date 2020/7/28 16:12
 */
public class EasyClientSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 设置扫描包
        EasyClientScannerConfigurer.setBasePackages(this.getBasePackages(annotationMetadata));
        return new String[]{ EasyClientScannerConfigurer.class.getName() };
    }


    private Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableEasyHttp.class.getCanonicalName());

        Set<String> basePackages = new HashSet<>();
        String defaultPackageName = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        if (MapUtils.isEmpty(attributes)) {
            basePackages.add(defaultPackageName);
            return basePackages;
        }

        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        if (basePackages.isEmpty()) {
            basePackages.add(defaultPackageName);
        }

        return basePackages;
    }
}
