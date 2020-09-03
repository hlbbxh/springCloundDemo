package com.hlbbxh.learnorder.repository;

import com.hlbbxh.learnorder.entity.OrderMaster;
import com.hlbbxh.learnorder.enums.OrderStatusEnum;
import com.hlbbxh.learnorder.enums.PayStatusEnum;
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
 * @date 2020-09-0221:16:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest extends TestCase {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void test01(){
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster save = orderMasterRepository.save(orderMaster);
        System.err.println(save.toString());
    }
}