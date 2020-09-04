### 学习笔记
 SpringCloud学习过程中存在的一些问题，和学习过程中的一些笔记，希望忘记的时候能有所回顾，学有所用，温故而知新；
 
### HTTP VS RPC
HTTP：分布式解决方案，就是SpringCloud提醒restful风格的api，有两种RestTemplate,和Feign这两个

RPC：远程调用过程，就是dubbo的体系的框架

### 服务之间的通讯之RestTemplate
使用 RestTemplate 通讯的方式有三种
```java
@RestController
@RequestMapping("/order")
public class OrderClinetController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public RestTemplate restTemplates;

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
}
```
第三种 使用注解 配置的方式
```java
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

### 客户端 负载均衡器 Ribbon
当调用服务的时候，有些服务会注册启动多个实例，这时候就需要有负载均衡器，来分配不同的服务器了；

RestTemplate,Feign,Zuul都使用到了Ribbon；

Ribbon的核心有三点，服务发现，服务选择规则，服务监听；

主要组件：ServerList,IRule,ServerListFilter；

默认就说轮询的方式进行服务器选择；










