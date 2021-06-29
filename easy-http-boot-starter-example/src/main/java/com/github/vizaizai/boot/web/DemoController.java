package com.github.vizaizai.boot.web;

import com.github.vizaizai.boot.service.BookHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.env.Environment;
import org.springframework.expression.*;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaochongwei
 * @date 2020/8/3 18:32
 */
@RestController
public class DemoController {

    @Autowired
    private BookHttpService bookHttpService;

    @Autowired
    private Environment environment;

    @GetMapping("/foo")
    public Object foo() {

//        ExpressionParser parser = new SpelExpressionParser();
//        StandardEvaluationContext context = new StandardEvaluationContext(environment);
//        context.addPropertyAccessor(new EnvironmentAccessor()); // ['api.addBooks']
//        //Expression expression = parser.parseExpression("${['api.addBooks']}wefewf", new TemplateParserContext("${","}"));
//        Expression expression = parser.parseExpression("['api.addBooks'] + ");
//        System.out.println(expression.getValue(context));

        //return "";
        return bookHttpService.getBookById("155664");
    }

    @GetMapping("/listBooks")
    public Object listBooks() {
        return bookHttpService.listBooks();
    }

}
