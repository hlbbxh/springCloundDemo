package com.hlbbxh.learnorder.enums;

/**
 * @author Carrot
 * @Title: 结果的异常
 * @Package
 * @Description:
 * @date 2020-09-0615:54:53
 */
public enum ResultEnum {
    PRODUCT_NOT_EXCEPTION(1,"商品不存在"),
    PRODUCT_STOCK_ERROR(2,"商品库存不够了")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
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
