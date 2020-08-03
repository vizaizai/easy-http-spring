package com.github.firelcw.annotation;


import com.github.firelcw.support.EasyClientSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EasyClientSelector.class)
public @interface EnableEasyHttp {
}
