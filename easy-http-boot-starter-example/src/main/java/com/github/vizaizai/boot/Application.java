package com.github.vizaizai.boot;

import com.github.vizaizai.boot.annotation.EnableEasyHttp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liaochongwei
 * @date 2020/8/3 18:25
 */
@SpringBootApplication
@EnableEasyHttp("com.github.vizaizai.boot.service")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
