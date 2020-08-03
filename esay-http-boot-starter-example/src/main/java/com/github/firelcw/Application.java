package com.github.firelcw;

import com.github.firelcw.annotation.EnableEasyHttp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liaochongwei
 * @date 2020/8/3 18:25
 */
@SpringBootApplication
@EnableEasyHttp
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
