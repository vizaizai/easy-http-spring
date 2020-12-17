package com.github.vizaizai.boot.autoconfigure;

import com.github.vizaizai.boot.support.InterceptorsBean;
import com.github.vizaizai.client.AbstractClient;
import com.github.vizaizai.client.ApacheHttpClient;
import com.github.vizaizai.codec.Decoder;
import com.github.vizaizai.codec.DefaultDecoder;
import com.github.vizaizai.codec.DefaultEncoder;
import com.github.vizaizai.codec.Encoder;
import com.github.vizaizai.interceptor.ErrorInterceptor;
import com.github.vizaizai.interceptor.LogInterceptor;
import com.github.vizaizai.model.HttpRequestConfig;
import com.github.vizaizai.retry.DefaultRule;
import com.github.vizaizai.retry.RetryTrigger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 廖重威
 * @date 2020/8/1 19:16
 */
@Configuration
@EnableConfigurationProperties({EasyHttpProperties.class})
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

    @ConditionalOnMissingBean
    @Bean
    RetryTrigger retryTrigger() {
        return new DefaultRule();
    }

}
