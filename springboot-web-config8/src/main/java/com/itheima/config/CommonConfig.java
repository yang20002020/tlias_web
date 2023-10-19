package com.itheima.config;

import com.itheima.service.DeptService;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //配置类
public class CommonConfig {

    //声明第三方bean
    @Bean
    public SAXReader reader(){
        return new SAXReader();
    }

}
