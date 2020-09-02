package com.hlbbxh.learnproduct.enums;

/**
 * @author Carrot
 * @Title: 商品上下架 的状态枚举
 * @Package
 * @Description:
 * @date 2020-09-0122:29:37
 */
public enum ProductStatus {

    UP(0,"在售"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
