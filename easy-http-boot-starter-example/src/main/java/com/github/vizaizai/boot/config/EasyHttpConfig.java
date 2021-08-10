package com.github.vizaizai.boot.config;

import com.github.vizaizai.client.AbstractClient;
import com.github.vizaizai.client.ApacheHttpClient;
import com.github.vizaizai.client.DefaultURLClient;
import com.github.vizaizai.codec.Decoder;
import com.github.vizaizai.codec.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaochongwei
 * @date 2020/8/7 12:34
 */
@Configuration
public class EasyHttpConfig {

//    @Bean
//    Decoder myDecoder() {
//        return new JacksonDecoder();
//    }

    @Bean
    AbstractClient client() {
        return ApacheHttpClient.getInstance();
    }
}
