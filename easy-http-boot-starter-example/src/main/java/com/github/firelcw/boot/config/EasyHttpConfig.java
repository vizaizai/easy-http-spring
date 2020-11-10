package com.github.firelcw.boot.config;

import com.github.firelcw.client.AbstractClient;
import com.github.firelcw.client.DefaultURLClient;
import com.github.firelcw.codec.Decoder;
import com.github.firelcw.codec.DefaultDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaochongwei
 * @date 2020/8/7 12:34
 */
@Configuration
public class EasyHttpConfig {

    @Bean
    Decoder myDecoder() {
        return new DefaultDecoder();
    }

    @Bean
    AbstractClient client() {
        return DefaultURLClient.getInstance();
    }
}
