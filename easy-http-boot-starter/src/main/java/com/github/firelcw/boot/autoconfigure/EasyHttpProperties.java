package com.github.firelcw.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author 廖重威
 * @date 2020/8/1 19:19
 */
@ConfigurationProperties(
        prefix = "easy-http"
)
public class EasyHttpProperties {
    /**
     * 扫描包
     */
    private String basePackage;
    /**
     * 基本路径
     */
    private String baseEndpoint = "localhost";
    /**
     * 基本路径列表
     */
    private Map<String,String> baseEndpoints;
    /**
     * 连接超时时间
     */
    private Integer connectTimeout = 15000;
    /**
     * 请求超时时间
     */
    private Integer requestTimeout = 15000;


    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    public void setBaseEndpoint(String baseEndpoint) {
        this.baseEndpoint = baseEndpoint;
    }

    public Map<String, String> getBaseEndpoints() {
        return baseEndpoints;
    }

    public void setBaseEndpoints(Map<String, String> baseEndpoints) {
        this.baseEndpoints = baseEndpoints;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }
}
