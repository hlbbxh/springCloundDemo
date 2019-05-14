package com.hlbbxh.product_date_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hlbbxh.product_date_server.entity.Product;
import com.hlbbxh.product_date_server.service.ProductService;

@RestController
public class ProductController {
  
	//service 层  
    @Autowired ProductService productService;
    
    /**
     * 一个方法  返回 所有的结果
     * @return
     */
    @RequestMapping("/products")
    public Object products() {
        List<Product> ps = productService.listProducts();
        return ps;
    }
}
