#### easy-http

##### 快速开始

> easy-http-boot-starter 是easy-http的springBoot版本，使用更简单，并且支持接口级别的拦截器和解码器

##### 1. 版本说明

| 项目 | easy-http-boot | spring-boot |
| ---- | -------------- | ----------- |
| 版本 | 1.X            | 2.1.X       |

##### 2. 安装

   ``` xml
<dependency>
  <groupId>com.github.firelcw</groupId>
  <artifactId>easy-http-boot-starter</artifactId>
  <version>1.1.4</version>
</dependency>
   ```

普通版请移步: [easy-http](https://github.com/firelcw/easy-http)

##### 3. 开始

3.1 配置

``` yaml
easy-http:
  base-package: com.github.firelcw.**.service #接口所在的包
#  base-endpoint: 127.0.0.1:8080 默认请求地址
  base-endpoints: #按客户端名字定义请求地址。如@EasyHttpClient(value = "book"),这个接口将使用127.0.0.1:8888
    book: 127.0.0.1:8888
```

3.2 编写接口

``` java
@EasyHttpClient(value = "book", interceptors = TimeInterceptor.class, decoderName = "myDecoder")
public interface BookHttpService {
    @Get("/books/{id}")
    ApiResult<Book> getBookById(@Var("id") String id);
}    
```

注意： `decoderName` 需要先配置一个bean

``` java
@Bean("myDecoder")
Decoder decoder() {
    return new DefaultDecoder();
}
```

3.3 引入使用

``` java
@Autowired
private BookHttpService bookHttpService;
```

##### 4. 核心用法
请移步: [easy-http](https://github.com/firelcw/easy-http)


#### 联系作者

如您有好的建议，或者有任何疑问，可联系我

- QQ: 274550900
- Email: liaochongwei666@163.com