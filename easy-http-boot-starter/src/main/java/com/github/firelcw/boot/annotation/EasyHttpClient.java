package com.github.firelcw.boot.annotation;

import com.github.firelcw.client.AbstractClient;
import com.github.firelcw.client.ApacheHttpClient;
import com.github.firelcw.codec.Decoder;
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
     * @return 解码器类型
     */
    Class<? extends Decoder> decoder() default Decoder.class;

    /**
     * @return 请求客户端类型
     */
    Class<? extends AbstractClient> client() default AbstractClient.class;



}