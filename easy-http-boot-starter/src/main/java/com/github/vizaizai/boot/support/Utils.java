package com.github.vizaizai.boot.support;

import com.github.vizaizai.exception.EasyHttpException;
import com.github.vizaizai.interceptor.HttpInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liaochongwei
 * @date 2020/8/4 15:12
 */
public class Utils {
    private Utils() {
    }

    /**
     * 创建拦截器
     * @param clazzList 拦截器类型
     * @return List<HttpInterceptor>
     */
   public static List<HttpInterceptor> createHttpInterceptors(Class<? extends HttpInterceptor>[]  clazzList) {
        List<HttpInterceptor> list;
        if (clazzList== null || clazzList.length == 0) {
            return Collections.emptyList();
        }
        list = new ArrayList<>();
        for (Class<? extends HttpInterceptor> clazz : clazzList) {
            try {
                list.add(clazz.newInstance());
            } catch (Exception e) {
                throw new EasyHttpException("instance HttpInterceptor failure");
            }
        }
        return list;

    }
}
