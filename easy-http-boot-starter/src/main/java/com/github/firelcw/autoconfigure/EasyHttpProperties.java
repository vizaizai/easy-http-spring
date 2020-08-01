package com.github.firelcw.autoconfigure;

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
    private String baseEndpoint;
    /**
     * 基本路径列表
     */
    private Map<String,String> baseEndpoints;

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
}
