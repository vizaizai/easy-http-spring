package com.github.firelcw.boot.annotation;


import com.github.firelcw.boot.support.EasyClientSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EasyClientSelector.class)
public @interface EnableEasyHttp {

    /**
     * Alias for the {@link #basePackages()} attribute. Allows for more concise annotation
     * declarations e.g.: {@code @ComponentScan("org.my.pkg")} instead of
     * {@code @ComponentScan(basePackages="org.my.pkg")}.
     * @return the array of 'basePackages'.
     */
    String[] value() default {};
    /**
     * Base packages to scan for annotated components.
     * @return the array of 'basePackages'.
     */
    String[] basePackages() default {};;
}
