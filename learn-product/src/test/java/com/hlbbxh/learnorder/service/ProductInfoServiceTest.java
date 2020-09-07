package com.hlbbxh.learnorder.service;

import com.hlbbxh.learnorder.DTO.CartDTO;
import com.hlbbxh.learnorder.LearnProductApplicationTests;
import com.hlbbxh.learnorder.entity.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:33:01
 */
@Component
public class ProductInfoServiceTest extends LearnProductApplicationTests {
    //这种测试继承 测试主类 使用 Component 注解 即可

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 测试查询所有的商品
     */
    @Test
    public void test01(){
        List<ProductInfo> upAll = productInfoService.findUpAll();
        System.err.println(upAll);
    }

    /**
     * 测试 按照 productid 查询商品
     */
    @Test
    public void test02(){
        List<ProductInfo> upAll = productInfoService.findByProductids(Arrays.asList("157875196366160022","157875227953464068"));
        System.err.println(upAll);
    }

    /**
     * 测试扣库存
     */
    @Test
    public void test03(){
        CartDTO cartDTO = new CartDTO();
        //皮蛋粥
        cartDTO.setProductId("157875196366160022");
        cartDTO.setProductQuantity(1);
        productInfoService.decreaseStock(Arrays.asList(cartDTO));
    }
}