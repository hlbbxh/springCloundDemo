package com.hlbbxh.learnproduct.service.impl;

import com.hlbbxh.learnproduct.entity.ProductInfo;
import com.hlbbxh.learnproduct.enums.ProductStatus;
import com.hlbbxh.learnproduct.repository.ProuductRepository;
import com.hlbbxh.learnproduct.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:27:00
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProuductRepository prouductRepository;

    /**
     * 查询所有的 在售商品
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
       return prouductRepository.findByProductStatus(ProductStatus.UP.getCode());
    }
}
