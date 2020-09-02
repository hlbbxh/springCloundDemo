package com.hlbbxh.learnproduct.service;

import com.hlbbxh.learnproduct.entity.ProductInfo;

import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:21:50
 */
public interface ProductInfoService {

    /**
     * 查询所有在售的商品
     */
    List<ProductInfo> findUpAll();
}
