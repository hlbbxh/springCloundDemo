package com.hlbbxh.learnproduct.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:47:31
 */
public class ProductVo {

    /**
     * 商品类名的 名称 返回给前端 的是 name
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     *
     */
    @JsonProperty("type")
    private Integer categoryType;

    // 写到这里了
}
