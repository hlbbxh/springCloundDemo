package com.hlbbxh.learnproduct.repository;

import com.hlbbxh.learnproduct.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:13:11
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    /**
     * 查询类目 使用的 是 in 传递的 是list
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
