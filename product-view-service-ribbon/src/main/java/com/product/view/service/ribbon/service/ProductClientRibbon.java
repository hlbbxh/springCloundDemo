package com.product.view.service.ribbon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.product.view.service.ribbon.entity.Product;
/**
 * 
 * @author 
 * Ribbon 客户端， 通过 restTemplate 访问 http://PRODUCT-DATA-SERVICE/products ，
 * 而 product-data-service 既不是域名也不是ip地址，而是 数据服务在 eureka 注册中心的名称。
 * 注意看，这里只是指定了要访问的 微服务名称，但是并没有指定端口号到底是 8001, 还是 8002.
 */
@Component
public class ProductClientRibbon {
 
    @Autowired
    RestTemplate restTemplate;
    
    /**
     * 数据服务在 eureka 注册中心的名称。
     * 但是并没有指定端口号到底是 8001, 还是 8002. 
     * 但是肯定是这个方法 PRODUCT-DATA-SERVICE 服务下的 /products 方法
     * @return
     */
    public List<Product> listProdcuts() {
        return restTemplate.getForObject("http://PRODUCT-DATA-SERVICE/products",List.class);
    }
 
}
