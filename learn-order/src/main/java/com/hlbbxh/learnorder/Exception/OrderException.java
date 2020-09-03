package com.hlbbxh.learnorder.Exception;

import com.hlbbxh.learnorder.enums.ResultEnum;

/**
 * @author Carrot
 * @Title:自定义异常 订单的
 * @Package
 * @Description:
 * @date 2020-09-0222:27:47
 */
public class OrderException extends RuntimeException{

    /**
     * 异常代码
     */
    private Integer errorCode;

    /**
     * 构造方法
     * @param errorCode
     * @param message
     */
    public OrderException(Integer errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 枚举的结果
     * @param resultEnum
     */
    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.errorCode = resultEnum.getCode();
    }


}
