package com.hlbbxh.learnorder.service.impl;

import com.hlbbxh.learnorder.DTO.CartDTO;
import com.hlbbxh.learnorder.DTO.OrderDTO;
import com.hlbbxh.learnorder.FeignClient.ProductClinet;
import com.hlbbxh.learnorder.entity.OrderDetail;
import com.hlbbxh.learnorder.entity.ProductInfo;
import com.hlbbxh.learnorder.service.OrderService;
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
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
     * 调用商品的 Feign 客户端
     */
    @Autowired
    private ProductClinet productClinet;
    /**
     * 创建订单的 操作
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // todo 查询商品信息 （调用商品服务）
        //获取购物车的所有的商品 id
        List<String> productids = orderDTO.getOrderDetails().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        //查询购物车商品
        List<ProductInfo> productInfos = productClinet.listOrderByProductIds(productids);

        // todo 计算总价
        //计算总价 先循环购物车 定义 总价为0
        BigDecimal orderTotal = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            //再循环商品数据
            for (ProductInfo productInfo : productInfos){
                //当商品 id 相等的时候才开始计算
                if(orderDetail.getProductId().equals(productInfo.getProductId())){
                    //多个商品 要累计 .add orderTotal 方法
                    orderTotal = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderTotal);
                    //放入 商品信息
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    //这是订单orderid 相同的
                    orderDetail.setOrderId(orderId);
                    //购物车 商品id 每个都不一样 直接生成
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // todo 扣库存（调用商品服务）
        List<CartDTO> cartDTOS = orderDTO.getOrderDetails().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClinet.decreaseStock(cartDTOS);

        OrderMaster orderMaster = new OrderMaster();
        //先设置 order id 在拷贝
        orderDTO.setOrderId(orderId);
        // 复制数据
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //放入总价 在上面计算的
        orderMaster.setOrderAmount(orderTotal);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster save = orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
