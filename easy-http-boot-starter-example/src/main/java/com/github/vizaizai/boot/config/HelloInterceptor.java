package com.github.vizaizai.boot.config;


import com.github.vizaizai.entity.HttpRequest;
import com.github.vizaizai.entity.HttpResponse;
import com.github.vizaizai.interceptor.HttpInterceptor;
import com.github.vizaizai.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author liaochongwei
 * @date 2020/7/31 14:06
 */
@Component
public class HelloInterceptor implements HttpInterceptor {
    private static final Logger log = LoggerFactory.getLogger(HelloInterceptor.class);
    @Override
    public boolean preHandle(HttpRequest request) {
        log.info("===============HELLO============");
        return true;
    }

    @Override
    public void postHandle(HttpRequest request, HttpResponse response) {

    }



}
