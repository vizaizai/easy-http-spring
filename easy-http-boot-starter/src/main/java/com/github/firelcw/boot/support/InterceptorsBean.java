package com.github.firelcw.boot.support;

import com.github.firelcw.interceptor.HttpInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置
 * @author liaochongwei
 * @date 2020/8/3 18:42
 */
public class InterceptorsBean {

    private List<HttpInterceptor> interceptors;

    public void addInterceptors(List<HttpInterceptor> interceptors) {
        if (this.interceptors == null) {
            this.interceptors = interceptors;
        }
    }

    public void addInterceptor(HttpInterceptor interceptor) {
        if (this.interceptors == null) {
            this.interceptors = new ArrayList<>();
        }
        this.interceptors.add(interceptor);
    }
    public List<HttpInterceptor> getInterceptors() {
        return this.interceptors;
    }
}
