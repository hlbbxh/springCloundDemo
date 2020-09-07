package com.hlbbxh.learnorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carrot
 * @Title: http://localhost:5000/ProductServer/getMsg
 * @Package
 * @Description:
 * @date 2020-09-0320:59:22
 */
@RestController
@RequestMapping("/product")
public class ProductServerController {

    @GetMapping("/getMsg")
    public String getMsg(){
        return "这是商品服务器";
    }


}
