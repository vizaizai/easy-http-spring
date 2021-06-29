package com.github.vizaizai.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
     * 基本路径
     */
    private String baseEndpoint;
    /**
     * 基本路径列表
     */
    private Map<String,String> baseEndpoints;
    /**
     * 打印请求日志
     */
    private boolean requestLog = false;
    /**
     * 连接超时时间
     */
    private Integer connectTimeout = 15000;
    /**
     * 请求超时时间
     */
    private Integer requestTimeout = 15000;
    /**
     * 字符编码
     */
    private Charset encoding = StandardCharsets.UTF_8;
    /**
     * 重试
     */
    @NestedConfigurationProperty
    private RetryProperties retry;


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

    public RetryProperties getRetry() {
        return retry;
    }

    public void setRetry(RetryProperties retry) {
        this.retry = retry;
    }

    public boolean isRequestLog() {
        return requestLog;
    }

    public void setRequestLog(boolean requestLog) {
        this.requestLog = requestLog;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }
}
