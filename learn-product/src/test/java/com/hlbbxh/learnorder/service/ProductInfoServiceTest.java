package com.hlbbxh.learnorder.service;

import com.hlbbxh.learnorder.LearnProductApplicationTests;
import com.hlbbxh.learnorder.entity.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Test
    public void test01(){
        List<ProductInfo> upAll = productInfoService.findUpAll();
        System.err.println(upAll);
    }
}