package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {

    @Bean
    //判断环境中是否存在jwts这个类，如果存在这个类，才会将该bean 注入到ioc容器当中
//    @ConditionalOnClass(name = "io.jsonwebtoken.Jwts")
//    @ConditionalOnMissingBean //如果不存在该类型的bean（这里是指HeaderParser） ，才会将该bean注入到ioc容器当中
    @ConditionalOnProperty(name = "name",havingValue = "itheima")//如果配置文件中有指定的属性和对应的值，才会将该bean注入到ioc容器当中
    public HeaderParser headerParser(){
        return new HeaderParser();
    }

    @Bean
    public HeaderGenerator headerGenerator(){
        return new HeaderGenerator();
    }
}
