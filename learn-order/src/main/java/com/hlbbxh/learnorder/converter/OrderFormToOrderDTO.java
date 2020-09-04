package com.hlbbxh.learnorder.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hlbbxh.learnorder.Exception.OrderException;
import com.hlbbxh.learnorder.controller.DTO.OrderDTO;
import com.hlbbxh.learnorder.controller.form.OrderForm;
import com.hlbbxh.learnorder.entity.OrderDetail;
import com.hlbbxh.learnorder.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0222:35:21
 */
public class OrderFormToOrderDTO {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 转换数据 前台的数据 转换成 dto
     * 注意 这里 static 的方法无法试试用 静态关键字
     * @param orderForm
     * @return
     */
    public OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            //转换 购物车 的程度json数据为 商品信息
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【json转换失败】,string={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        //购买的商品 列表
        orderDTO.setOrderDetails(orderDetailList);
        return orderDTO;
    }
}
