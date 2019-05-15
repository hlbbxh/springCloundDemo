package com.hlbbxh.product_view_service_feign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlbbxh.product_view_service_feign.entity.Product;
import com.hlbbxh.product_view_service_feign.service.ProductService;

@Controller
public class ProductController {
	
    @Autowired ProductService productService;
    
    //获取配置服务器的配置信息
    @Value("${version}")
    String version;
    
    @RequestMapping("/products")
    public Object products(Model m) {
        List<Product> ps = productService.listProducts();
        m.addAttribute("ps", ps);
        //放入作用域
        m.addAttribute("version", version);
        return "products";
    }
}