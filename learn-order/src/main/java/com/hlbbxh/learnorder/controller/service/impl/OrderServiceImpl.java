package com.hlbbxh.learnorder.controller.service.impl;

import com.hlbbxh.learnorder.controller.DTO.OrderDTO;
import com.hlbbxh.learnorder.controller.service.OrderService;
import com.hlbbxh.learnorder.entity.OrderMaster;
import com.hlbbxh.learnorder.enums.OrderStatusEnum;
import com.hlbbxh.learnorder.enums.PayStatusEnum;
import com.hlbbxh.learnorder.repository.OrderDetailRepository;
import com.hlbbxh.learnorder.repository.OrderMasterRepository;
import com.hlbbxh.learnorder.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0222:03:07
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /**
     * 创建订单的 操作
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // todo 查询商品信息 （调用商品服务）
        // todo 计算总价
        // todo 扣库存（调用商品服务）
        OrderMaster orderMaster = new OrderMaster();
        //先设置 order id 在拷贝
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        // 复制数据
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster save = orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
