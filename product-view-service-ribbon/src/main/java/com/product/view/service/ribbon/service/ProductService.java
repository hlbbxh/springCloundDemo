package com.product.view.service.ribbon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.view.service.ribbon.entity.Product;
/**
 * @author 
 * 服务类，数据从 ProductClientRibbon 中获取
 */
@Service
public class ProductService {
	
	//具体是用这个家伙来负载均衡的   挂掉的 不会再 请求  自动加入新的集群
    @Autowired ProductClientRibbon productClientRibbon;
    
    //Ribbon 客户端
    //productClientRibbon 里面的 listProdcuts 方法
    public List<Product> listProducts(){
        return productClientRibbon.listProdcuts();
 
    }
}