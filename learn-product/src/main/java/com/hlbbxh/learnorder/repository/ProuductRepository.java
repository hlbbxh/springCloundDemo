package com.hlbbxh.learnorder.repository;

import com.hlbbxh.learnorder.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Carrot
 * @Title: jpa Repository 按照方法名来查询数据的
 * @Package
 * @Description:
 * @date 2020-09-0121:58:22
 */
public interface ProuductRepository extends JpaRepository<ProductInfo,String> {
    //按照商品状态来查询数据  0 是正常的  1 是不正常的
    List<ProductInfo> findByProductStatus(Integer productStatus);

    //按照商品id获取商品的信息
    List<ProductInfo> findByProductIdIn(List<String> productidList);
}
