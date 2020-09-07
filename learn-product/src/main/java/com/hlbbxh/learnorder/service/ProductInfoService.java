package com.hlbbxh.learnorder.service;

import com.hlbbxh.learnorder.DTO.CartDTO;
import com.hlbbxh.learnorder.entity.ProductInfo;

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

    /**
     * 按照 商品id 查询商品信息
     * @return
     */
    List<ProductInfo> findByProductids(List<String> productIdList);

    /**
     * 下单购物车扣库存
     */
    void decreaseStock(List<CartDTO> cartslist);
}
