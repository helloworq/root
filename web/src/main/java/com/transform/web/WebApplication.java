package com.transform.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/application.properties")
@EnableDubbo(scanBasePackages = "com.transform.web")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
