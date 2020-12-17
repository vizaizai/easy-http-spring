package com.github.vizaizai.boot.autoconfigure;

/**
 * @author 廖重威
 * @date 2020/12/17 19:19
 */
public class RetryProperties {
    /**
     * 是否启动
     */
    private boolean enable = false;
    /**
     * 最大重试次数
     */
    private Integer maxAttempts = 3;
    /**
     * 间隔时间(ms)
     */
    private Integer intervalTime = 10;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }
}
