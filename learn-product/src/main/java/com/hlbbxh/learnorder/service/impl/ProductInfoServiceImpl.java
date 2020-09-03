package com.hlbbxh.learnorder.service.impl;

import com.hlbbxh.learnorder.entity.ProductInfo;
import com.hlbbxh.learnorder.enums.ProductStatus;
import com.hlbbxh.learnorder.repository.ProuductRepository;
import com.hlbbxh.learnorder.service.ProductInfoService;
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
