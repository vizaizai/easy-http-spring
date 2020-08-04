package com.github.firelcw.boot.annotation;


import com.github.firelcw.boot.support.EasyClientSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EasyClientSelector.class)
public @interface EnableEasyHttp {
}
