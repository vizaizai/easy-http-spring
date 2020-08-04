package com.github.firelcw.boot.annotation;

import com.github.firelcw.interceptor.HttpInterceptor;

import java.lang.annotation.*;

/**
 * @author liaochongwei
 * @date 2020/8/4 14:57
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyHttpClient {
    /**
     * @return 客户端名称
     */
    String value() default "";
    /**
     * @return 拦截器
     */
    Class<? extends HttpInterceptor>[] interceptors() default {};
    /**
     *
     * @return 解码器名称，将覆盖全局的解码器(需先配置解码器)
     */
    String decoderName() default "";




}