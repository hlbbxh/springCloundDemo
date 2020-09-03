package com.hlbbxh.learnorder.repository;

import com.hlbbxh.learnorder.entity.OrderDetail;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0221:11:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest extends TestCase {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void test01(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12367");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);
        OrderDetail save = orderDetailRepository.save(orderDetail);
        System.out.println(save.toString());
    }
}