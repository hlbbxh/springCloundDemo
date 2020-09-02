package com.hlbbxh.learnproduct.repository;

import com.hlbbxh.learnproduct.entity.ProductCategory;
import com.hlbbxh.learnproduct.entity.ProductInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest extends TestCase {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 测试按照 类别 获取  所有的类别
     */
    @Test
    public void test01(){
        List<ProductCategory> byCategoryTypeIn1 = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,12));
        List<ProductCategory> byCategoryTypeIn2 = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,22));
        System.out.println(byCategoryTypeIn1.toString());
        System.out.println(byCategoryTypeIn2.toString());
    }
}