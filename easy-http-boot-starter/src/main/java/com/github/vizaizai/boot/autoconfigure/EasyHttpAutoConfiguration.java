package com.github.vizaizai.boot.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vizaizai.boot.support.EnvironmentPathConverter;
import com.github.vizaizai.boot.support.InterceptorsBean;
import com.github.vizaizai.boot.support.SpringInterceptorGenerator;
import com.github.vizaizai.client.AbstractClient;
import com.github.vizaizai.client.ApacheHttpClient;
import com.github.vizaizai.codec.Decoder;
import com.github.vizaizai.codec.Encoder;
import com.github.vizaizai.codec.JacksonDecoder;
import com.github.vizaizai.codec.JacksonEncoder;
import com.github.vizaizai.entity.HttpRequestConfig;
import com.github.vizaizai.hander.mapping.PathConverter;
import com.github.vizaizai.interceptor.ErrorInterceptor;
import com.github.vizaizai.interceptor.InterceptorGenerator;
import com.github.vizaizai.retry.DefaultRule;
import com.github.vizaizai.retry.RetryTrigger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author 廖重威
 * @date 2020/8/1 19:16
 */
@Configuration
@EnableConfigurationProperties({EasyHttpProperties.class})
public class EasyHttpAutoConfiguration {

    @ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
    @ConditionalOnMissingBean
    @Bean
    Decoder springJacksonDecoder(ObjectMapper objectMapper) {
        return new JacksonDecoder(objectMapper);
    }

    @ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
    @ConditionalOnMissingBean
    @Bean
    Encoder springJacksonEncoder(ObjectMapper objectMapper) {
        return new JacksonEncoder(objectMapper);
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
        config.setEncoding(properties.getEncoding());
        return config;
    }

    @ConditionalOnMissingBean
    @Bean
    InterceptorsBean interceptorsBean() {
        InterceptorsBean interceptorsBean = new InterceptorsBean();
        interceptorsBean.addInterceptor(new ErrorInterceptor());
        return interceptorsBean;
    }

    @ConditionalOnMissingBean
    @Bean
    RetryTrigger retryTrigger() {
        return new DefaultRule();
    }

    @ConditionalOnMissingBean
    @Bean
    PathConverter pathConverter(Environment environment) {
        return new EnvironmentPathConverter(environment);
    }

    @ConditionalOnMissingBean
    @Bean
    InterceptorGenerator interceptorGenerator() {
        return new SpringInterceptorGenerator();
    }

}
