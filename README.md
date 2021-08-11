#### easy-http

##### 快速开始

> easy-http-boot-starter 是easy-http的springBoot版本，使用更简单，并且支持接口级别的拦截器和解码器

##### 1. 版本说明

| 项目 | easy-http-boot | spring-boot |
| ---- | -------------- | ----------- |
| 版本 | 1.X            | 2.1.X+      |

##### 2. 安装

   ``` xml
<dependency>
  <groupId>com.github.vizaizai</groupId>
  <artifactId>easy-http-boot-starter</artifactId>
  <version>1.6.8</version>
</dependency>
   ```

普通版请移步: [easy-http](https://github.com/vizaizai/easy-http)

##### 3. 开始

3.1 配置

开启：启动类或者配置上注解`@EnableEasyHttp`  value是包路径，默认是被注解包的路径。

``` yaml
easy-http:
  base-endpoint: http://127.0.0.1:8080
  #base-endpoints: #按客户端名字定义请求地址。如@EasyHttpClient(value = "book"),这个接口将使用127.0.0.1:8888
  # book: http://127.0.0.1:8888
  retry:
    enable: true #是否开启重试
    max-attempts: 1
    interval-time: 0
  request-log: false  #是否开启请求日志
```

3.2 编写接口

``` java
@EasyHttpClient
public interface BookHttpService {
    @Get("/books/{id}")
    ApiResult<Book> getBookById(@Var("id") String id);
}    
```

3.3 注入使用

``` java
@Autowired
private BookHttpService bookHttpService;
```

##### 4. 核心用法
请移步: [easy-http](https://github.com/vizaizai/easy-http)


#### 联系作者

如您有好的建议，或者有任何疑问，可联系我

- QQ: 274550900
- Email: liaochongwei666@163.com