package com.github.firelcw.boot.autoconfigure;

import com.github.firelcw.boot.support.InterceptorsBean;
import com.github.firelcw.client.AbstractClient;
import com.github.firelcw.client.ApacheHttpClient;
import com.github.firelcw.codec.Decoder;
import com.github.firelcw.codec.DefaultDecoder;
import com.github.firelcw.codec.DefaultEncoder;
import com.github.firelcw.codec.Encoder;
import com.github.firelcw.interceptor.ErrorInterceptor;
import com.github.firelcw.interceptor.LogInterceptor;
import com.github.firelcw.model.HttpRequestConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 廖重威
 * @date 2020/8/1 19:16
 */
@Configuration
@EnableConfigurationProperties(EasyHttpProperties.class)
public class EasyHttpAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    Decoder defaultDecoder() {
        return new DefaultDecoder();
    }

    @ConditionalOnMissingBean
    @Bean
    Encoder encoder() {
        return new DefaultEncoder();
    }

    @ConditionalOnMissingBean
    @Bean
    AbstractClient defaultClient() {
        return ApacheHttpClient.getInstance();
    }

    @ConditionalOnMissingBean
    @Bean
    HttpRequestConfig config(EasyHttpProperties properties) {
        HttpRequestConfig config = new HttpRequestConfig();
        config.setConnectTimeout(properties.getConnectTimeout());
        config.setRequestTimeout(properties.getRequestTimeout());
        return config;
    }

    @ConditionalOnMissingBean
    @Bean
    InterceptorsBean interceptorsBean() {
        InterceptorsBean interceptorsBean = new InterceptorsBean();
        interceptorsBean.addInterceptor(new LogInterceptor());
        interceptorsBean.addInterceptor(new ErrorInterceptor());
        return interceptorsBean;
    }
}
