package com.hlbbxh.learnorder.controller;

import com.hlbbxh.learnorder.DTO.CartDTO;
import com.hlbbxh.learnorder.FeignClient.ProductClinet;
import com.hlbbxh.learnorder.entity.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0321:03:08
 */
@RestController
@RequestMapping("/order")
public class OrderClinetController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public RestTemplate restTemplates;

    /**
     * 调用的 feign Clinet 进行通讯
     */
    @Autowired
    public ProductClinet productClinet;

    @GetMapping("/getProductList")
    public String getProductList(){
        //1.第一种 远程调用 使用  这种方式不好。不知道ip地址 而且会启动多个实例，无法负载均衡（直接写死）
        RestTemplate restTemplate = new RestTemplate();
        //调用 getForObject 方法填写 请求的url和返回的数据类型class
        String forObject1 = restTemplate.getForObject("http://localhost:5000/ProductServer/getMsg", String.class)+"这是第一种";
        logger.info("response={}",forObject1);

        //2.第二种 使用 loadBalancerClient 来构造url（使用loadBalancerClient获取url）
        //调用 choose 来选择 Eureka 中的 application name
        ServiceInstance serviceInstance = loadBalancerClient.choose("LEARN-PRODUCT");
        //构造url
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/ProductServer/getMsg";
        //请求获取返回值
        String forObject2 = restTemplate.getForObject(url, String.class)+"这是第二种";
        logger.info("response={}",forObject2);

        //3.第三种方式 使用 @LoadBalancer 注解的方式 添加配置列类 RestTemplateConfig url 就可以直接写 application 的名字 注意 这个 是 上面的的 restTemplates
        String forObject3 = restTemplates.getForObject("http://LEARN-PRODUCT/ProductServer/getMsg", String.class) + "这是第三种";
        return "订单：" + forObject3;
    }

    /**
     * 测试使用 Feign 来调用客户端通讯
     * @return
     */
    @GetMapping("/testFeign")
    public String testFeign(){
        String msgPro = productClinet.getMsgPro();
        return msgPro+"这是使用的Feign方式";
    }

    /**
     * 【测试】按照商品id获取商品信息  http://localhost:5001/order/testListOrderByProductIds
     * @return
     */
    @GetMapping("/testListOrderByProductIds")
    public List<ProductInfo> testListOrderByProductIds(){
        List<ProductInfo> productInfos = productClinet.listOrderByProductIds(Arrays.asList("164103465734242707"));
        return productInfos;
    }

    /**
     * 【测试】扣库存
     * @return
     */
    @GetMapping("/testDecreaseStock")
    public String testDecreaseStock(){
        CartDTO cartDTO = new CartDTO();
        //皮蛋粥
        cartDTO.setProductId("157875196366160022");
        cartDTO.setProductQuantity(1);
        productClinet.decreaseStock(Arrays.asList(cartDTO));
        return "success";
    }
}
