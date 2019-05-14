package com.hlbbxh.product_view_service_feign.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.hlbbxh.product_view_service_feign.entity.Product;

@FeignClient(value = "PRODUCT-DATA-SERVICE")
public interface ProductClientFeign {
 
    @GetMapping("/products")
    public List<Product> listProdcuts();
}