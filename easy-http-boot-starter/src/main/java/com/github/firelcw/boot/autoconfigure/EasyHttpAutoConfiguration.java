package com.github.firelcw.boot.autoconfigure;

import com.github.firelcw.codec.Decoder;
import com.github.firelcw.codec.DefaultDecoder;
import com.github.firelcw.codec.DefaultEncoder;
import com.github.firelcw.codec.Encoder;
import com.github.firelcw.interceptor.ErrorInterceptor;
import com.github.firelcw.interceptor.TimeInterceptor;
import com.github.firelcw.model.HttpRequestConfig;
import com.github.firelcw.boot.support.InterceptorsBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author 廖重威
 * @date 2020/8/1 19:16
 */
@Configuration
@EnableConfigurationProperties(EasyHttpProperties.class)
public class EasyHttpAutoConfiguration {



    @Bean("defaultDecoder")
    @Primary
    Decoder decoder() {
        return new DefaultDecoder();
    }

    @ConditionalOnMissingBean
    @Bean
    Encoder encoder() {
        return new DefaultEncoder();
    }

    @ConditionalOnMissingBean
    @Bean
    HttpRequestConfig config(EasyHttpProperties properties) {
        HttpRequestConfig config = new HttpRequestConfig();
        config.setConnectTimeout(properties.getConnectTimeout());
        config.setSocketTimeout(properties.getSocketTimeout());
        return config;
    }

    @ConditionalOnMissingBean
    @Bean
    InterceptorsBean interceptorsBean() {
        InterceptorsBean interceptorsBean = new InterceptorsBean();
        interceptorsBean.addInterceptor(new TimeInterceptor());
        interceptorsBean.addInterceptor(new ErrorInterceptor());
        return interceptorsBean;
    }
}
