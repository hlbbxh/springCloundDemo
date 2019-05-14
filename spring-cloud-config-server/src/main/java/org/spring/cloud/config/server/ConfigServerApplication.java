package org.spring.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @author 配置文件 服务器
 * 主要是 @EnableConfigServer 这个注解表示本springboot 是个配置服务器。
 * 先启动 EurekaServerApplication， 再启动 ConfigServerApplication， 然后访问
 *	http://localhost:8030/version/dev    
 *	得到 {"name":"version","profiles":["dev"],"label":null,"version":"1588adf54e80a56331bf5f8627d8a50afa79ecd3","state":null,"propertySources":[]}
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
public class ConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
	//http://localhost:8030/version/dev
}