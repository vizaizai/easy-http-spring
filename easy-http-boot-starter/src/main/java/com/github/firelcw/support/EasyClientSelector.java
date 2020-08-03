package com.github.firelcw.support;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liaochongwei
 * @date 2020/7/28 16:12
 */
public class EasyClientSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 设置一些参数
        return new String[]{ EasyClientScannerConfigurer.class.getName() };
    }

}
