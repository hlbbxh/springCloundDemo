package com.hlbbxh.learnorder.Exception;

import com.hlbbxh.learnorder.enums.ResultEnum;

/**
 * @author Carrot
 * @Title:ProductException 商品异常
 * @Package
 * @Description:
 * @date 2020-09-0615:52:43
 */
public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 返回吗，枚举的 异常信息
     * @param resultEnum
     */
    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
