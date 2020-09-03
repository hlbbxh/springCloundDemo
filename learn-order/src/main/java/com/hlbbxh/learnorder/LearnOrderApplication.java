package com.hlbbxh.learnorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//EnableEurekaClient 代表eureka 客户端
@EnableEurekaClient
public class LearnOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnOrderApplication.class, args);
    }

}
