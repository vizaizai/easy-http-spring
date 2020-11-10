package com.github.firelcw.boot.service;

import com.github.firelcw.annotation.*;
import com.github.firelcw.boot.annotation.EasyHttpClient;
import com.github.firelcw.boot.model.ApiResult;
import com.github.firelcw.boot.model.Book;
import com.github.firelcw.client.DefaultURLClient;
import com.github.firelcw.codec.DefaultDecoder;
import com.github.firelcw.interceptor.TimeInterceptor;

import java.util.List;
import java.util.Map;

/**
 * @author liaochongwei
 * @date 2020/8/3 10:27
 */
@EasyHttpClient(value = "book", interceptors = TimeInterceptor.class,
        decoder = DefaultDecoder.class, client = DefaultURLClient.class)
public interface BookHttpService {

    @Get("/books/{id}")
    ApiResult<Book> getBookById(@Var("id") String id);

    @Get("/books?author={author}")
    ApiResult<List<Book>> listBooksByAuthor(@Var("author") String author);

    @Get("/books")
    ApiResult<List<Book>> listBooksByAuthor(@Query Map<String, String> params);

    @Post("/books")
    void addBook(@Body Book book);

    @Delete("/books/{id}")
    ApiResult<Void> deleteBookById(@Var("id") String id);

    @Put("/books")
    ApiResult<Void> editBook(@Body Book book);


    @Post("/books")
    ApiResult<Void> addBook(@Headers Map<String, String> headers);

}
