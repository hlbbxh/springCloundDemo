package com.hlbbxh.product_date_server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hlbbxh.product_date_server.entity.Product;
/**
 * @author 
 *   服务类提供一个 Product 集合。 需要注意的是，这里把 端口号 放进了产品信息里。 
 *   这个数据服务会做成集群，那么访问者为了分辨到底是从哪个数据微服务取的数据，就需要提供个端口号，
 *   才能意识到是从不同的微服务得到的数据。
 */
@Service
public class ProductService {
	
    @Value("${server.port}")
    String port;
    
    /**
     * 获取到当前运行服务的端口号
     * @return
     */
    public List<Product> listProducts(){
        List<Product> ps = new ArrayList<>();
        ps.add(new Product(1,"请求成功，端后号是:"+port, 50));
        ps.add(new Product(2,"请求成功，端后号是:"+port, 150));
        ps.add(new Product(3,"请求成功，端后号是:"+port, 250));
        return ps;
    }
}