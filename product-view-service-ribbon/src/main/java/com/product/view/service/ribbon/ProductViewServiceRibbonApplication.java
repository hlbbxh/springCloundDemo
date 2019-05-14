package com.product.view.service.ribbon;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
 
@SpringBootApplication
@EnableEurekaClient     // 代表 Eureka 客户端
@EnableDiscoveryClient //  @EnableDiscoveryClient， 表示用于发现eureka 注册中心的微服务。
public class ProductViewServiceRibbonApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(ProductViewServiceRibbonApplication.class, args);
	}
	
	
    /**
     * 还多了个 RestTemplate，就表示用 restTemplate 这个工具来做负载均衡
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
     
}