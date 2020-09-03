package com.hlbbxh.learnorder.service;

import com.hlbbxh.learnorder.LearnProductApplicationTests;
import com.hlbbxh.learnorder.entity.ProductCategory;
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
 * @date 2020-09-0122:42:05
 */
@Component
public class ProductCategoryServiceTest extends LearnProductApplicationTests {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void test01(){
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        System.err.println(byCategoryTypeIn);
    }
}