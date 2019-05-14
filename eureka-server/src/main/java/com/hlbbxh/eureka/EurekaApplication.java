package com.hlbbxh.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 该类是注册中心
 * 主需要启动就可以了
 */
@SpringBootApplication
@EnableEurekaServer//代表是服务注册中的server EurekaServer 启动ta 后 就可以 通过配置文件的地址进行访问并管理 注册中心了
public class EurekaApplication {
public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}