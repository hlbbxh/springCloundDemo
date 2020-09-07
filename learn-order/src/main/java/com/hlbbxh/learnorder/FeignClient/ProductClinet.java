package com.hlbbxh.learnorder.FeignClient;

import com.hlbbxh.learnorder.DTO.CartDTO;
import com.hlbbxh.learnorder.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Carrot
 * @Title: 这是使用 Feign 的方式进行其他微服务的调用
 * @Package
 * @Description:
 * @date 2020-09-0614:13:15
 */
//调用 LEARN-PRODUCT 服务的里面的接口
@FeignClient(name = "LEARN-PRODUCT")
@RequestMapping("/product")
public interface ProductClinet {

    /**
     * 【调用商品的服务】调用商品下面的 什么方法 是按照url来定义的 方法名称 可以 不和那边保存一致
     * @return
     */
    @GetMapping("/getMsg")
    public String getMsgPro();

    /**
     * 【调用商品的服务】按照 注意参数的 设置 productidList 和 注解 RequestBody
     * @return
     */
    @PostMapping("/listOrderByProductIds")
    public List<ProductInfo> listOrderByProductIds(@RequestBody List<String> productidList);

    /**
     * 【调用商品的服务】 旧库存
     * @param listCarts
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> listCarts);
}
