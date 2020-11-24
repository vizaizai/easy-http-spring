package com.github.firelcw.boot;

import com.github.firelcw.boot.annotation.EnableEasyHttp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liaochongwei
 * @date 2020/8/3 18:25
 */
@SpringBootApplication
@EnableEasyHttp("com.github.firelcw.boot.service")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
