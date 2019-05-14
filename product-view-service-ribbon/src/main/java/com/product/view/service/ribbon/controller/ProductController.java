package com.product.view.service.ribbon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.view.service.ribbon.entity.Product;
import com.product.view.service.ribbon.service.ProductService;

@Controller
public class ProductController {
	//这里面的 方法 调用 的是 product-date-server 的/products 接口
    @Autowired ProductService productService;
    /**
     * 同样的控制器 只是逻辑不同 返回的结果不同
     * @param m
     * @return
     */
    @RequestMapping("/products")
    public Object products(Model m) {
        List<Product> ps = productService.listProducts();
        m.addAttribute("ps", ps);
        return "products";
    }
}