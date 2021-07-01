package com.github.vizaizai.boot.support;

import com.github.vizaizai.hander.mapping.PathConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 环境路径转换器
 * @author liaochongwei
 * @date 2021/6/24 17:00
 */
public class EnvironmentPathConverter implements PathConverter {
    /**
     * 上下文环境
     */
    private final Environment environment;

    public EnvironmentPathConverter(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String get(String value) {
        // 如果不是${}包裹，则需要属性解析
        if (StringUtils.isBlank(value)){
            return value;
        }

        StringBuilder stringExpression = new StringBuilder(value);
        // ${ 的开始下标
        int sIndex = -1;
        // 替换次数
        int len = 0;
        for (int i = 0; i < value.length(); i++) {
            // 匹配左边
            if (value.charAt(i) == '$' && i + 1 < value.length() && value.charAt(i + 1) == '{') {
                sIndex = i;
            }
            // 匹配到左边同时匹配到右边, 替换符号。左边 ['  右边 ']
            if (value.charAt(i) == '}' && sIndex != -1) {
                //  每进行一次替换，下标移动1
                int index1 = sIndex + len;
                int index2 = i + len;
                stringExpression.replace(index1, index1 + 2, "['");
                stringExpression.replace(index2, index2 + 1, "']");
                // 重置左下标
                sIndex = -1;
                len ++;
            }
        }
        // 无需替换，直接返回字符串
        if (len == 0) {
            return value;
        }

        // spEl解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 从标准的上下文取
        StandardEvaluationContext context = new StandardEvaluationContext(environment);
        // 添加环境访问器
        context.addPropertyAccessor(new EnvironmentAccessor());
        // 解析表达式
        Expression expression = parser.parseExpression(stringExpression.toString());

        return (String) expression.getValue(context);
    }
}
