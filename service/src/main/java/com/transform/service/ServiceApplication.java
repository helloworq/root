package com.transform.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EntityScan("com.transform.api")//扫描实体类包
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
