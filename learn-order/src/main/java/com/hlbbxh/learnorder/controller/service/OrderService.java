package com.hlbbxh.learnorder.controller.service;

import com.hlbbxh.learnorder.controller.DTO.OrderDTO;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0221:55:46
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO createOrder(OrderDTO orderDTO);
}
