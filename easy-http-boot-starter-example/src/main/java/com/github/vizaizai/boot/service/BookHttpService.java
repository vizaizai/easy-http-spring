package com.github.vizaizai.boot.service;

import com.github.vizaizai.annotation.*;
import com.github.vizaizai.boot.annotation.EasyHttpClient;
import com.github.vizaizai.boot.model.ApiResult;
import com.github.vizaizai.boot.model.Book;
import com.github.vizaizai.client.ApacheHttpClient;
import com.github.vizaizai.codec.JacksonDecoder;

import java.util.List;
import java.util.Map;

/**
 * @author liaochongwei
 * @date 2020/8/3 10:27
 */
@EasyHttpClient(decoder = JacksonDecoder.class, client = ApacheHttpClient.class)
public interface BookHttpService {

    //@Get("'http://' + ${host.system2} + ${api.addBooks}")
    @Get("/books/{id}")
    ApiResult<Book> getBookById(@Var String id);

    @Get("/books?author={author}")
    ApiResult<List<Book>> listBooksByAuthor(@Var("author") String author);

    @Get("/books")
    ApiResult<List<Book>> listBooks();

    @Post("/books")
    void addBook(@Body Book book);

    @Delete("/books/{id}")
    ApiResult<Void> deleteBookById(@Var("id") String id);

    @Put("/books")
    ApiResult<Void> editBook(@Body Book book);


    @Post("/books")
    ApiResult<Void> addBook(@Headers Map<String, String> headers);

}
