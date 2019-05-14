package com.hlbbxh.product_view_service_feign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlbbxh.product_view_service_feign.entity.Product;

@Service
public class ProductService {
    @Autowired ProductClientFeign productClientFeign;
    /**
     * 调用
     * @return
     */
    public List<Product> listProducts(){
        return productClientFeign.listProdcuts();
    }
}