package com.hlbbxh.learnorder.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
     * 商品种类
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 商品信息
     */
    @JsonProperty("foods")
    List<ProductInfoVo> productInfoVoList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVo> getProductInfoVoList() {
        return productInfoVoList;
    }

    public void setProductInfoVoList(List<ProductInfoVo> productInfoVoList) {
        this.productInfoVoList = productInfoVoList;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", productInfoVoList=" + productInfoVoList +
                '}';
    }
}
