package com.github.firelcw.boot.web;

import com.github.firelcw.boot.service.BookHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaochongwei
 * @date 2020/8/3 18:32
 */
@RestController
public class DemoController {

    @Autowired
    private BookHttpService bookHttpService;

    @GetMapping("/foo")
    public Object foo() {
        return bookHttpService.getBookById("155664");
    }
}
