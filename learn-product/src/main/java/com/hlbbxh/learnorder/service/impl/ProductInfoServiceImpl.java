package com.hlbbxh.learnorder.service.impl;

import com.hlbbxh.learnorder.DTO.CartDTO;
import com.hlbbxh.learnorder.Exception.ProductException;
import com.hlbbxh.learnorder.entity.ProductInfo;
import com.hlbbxh.learnorder.enums.ProductStatus;
import com.hlbbxh.learnorder.enums.ResultEnum;
import com.hlbbxh.learnorder.repository.ProuductRepository;
import com.hlbbxh.learnorder.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    /**
     * 按照 商品id 查询商品信息
     * @return
     */
    @Override
    public List<ProductInfo> findByProductids(List<String> productIdList) {
        return prouductRepository.findByProductIdIn(productIdList);
    }

    /**
     * 旧库存的操作 下单的时候
     * @param cartslist
     */
    @Override
    //事务操作  javax.transaction.Transactional
    @Transactional
    public void decreaseStock(List<CartDTO> cartslist) {
        for (int i = 0; i < cartslist.size() ; i++) {
            CartDTO cartDTO = cartslist.get(i);
            //扣库存之前 要先查询 还有没有库存
            Optional<ProductInfo> productInfoOptional = prouductRepository.findById(cartDTO.getProductId());
            //判断找到商品了没有
            if(!productInfoOptional.isPresent()){
                //抛出异常 商品不存在
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXCEPTION);
            }
            ProductInfo productInfo = productInfoOptional.get();
            //剩余数量
            Integer stockCount = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(stockCount<0){
                //商品库存不够了
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //重新保存进入数据库
            productInfo.setProductStock(stockCount);
            prouductRepository.save(productInfo);
        }
    }

}
