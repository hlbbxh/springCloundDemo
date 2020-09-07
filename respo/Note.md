### 学习笔记
SpringCloud学习过程中存在的一些问题，和学习过程中的一些笔记，希望忘记的时候能有所回顾，学有所用，温故而知新；这个篇章主要是记录的，注册中心的
一些配置Eureka，和远程调用RestTemplate，和Feign的基本使用和操作；
 
 
### Eureka 的一些配置
```yaml
server:
  port: 8878

eureka:
  instance:
    # 主机名 自定义链接
    hostname: localhost
  client:
    # 自己要不要注册
    # 注册地址
    serviceUrl:
      # http://eureka1:8001/
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
    # 是否将自己注册到Eureka Server 默认为true
    register-with-eureka: true
    #是否从Eureka Server查询注册信息
    fetch-registry: false

  server:
    # 关闭自我保护模式
    enable-self-preservation: false
    # 清理服务间隔4s，默认60*10000
    eviction-interval-timer-in-ms: 4000

#  dashboard:
#    path: /eureka

spring:
  application:
    # 服务名称
    name: eureka-server
```
Eureka的集群操作，由于Eureka是注册中心，不可以进行单一节点的部署和发布，所以最少需要两个或者三个集群进行同同时工作，相互注册
```yaml
server:
  # 端口
  port: 8001
eureka:
  instance:
    # 主机名
    hostname: localhost
    #访问路径可以显示ip地址
    prefer-ip-address: true
  client:
    # 注册地址
    serviceUrl:
      defaultZone: http://localhost:8002/eureka/,http://localhost:8003/eureka/
    # 是否将自己注册到Eureka Server 默认为true
    register-with-eureka: true
    #是否从Eureka Server查询注册信息
    fetch-registry: false
  server:
    # 关闭自我保护模式
    enable-self-preservation: false
    # 清理服务间隔4s，默认60*10000
    eviction-interval-timer-in-ms: 4000

spring:
  application:
    # 服务名称 应用名称，也是服务注册的名称
    name: eureka-server
```
客户端进注册需要在多个集群上同时进行注册，在      defaultZone: http://localhost:8878/eureka,http://localhost:8879/eureka
这样子同事向多个注册中心注册即可




 
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


### 服务之间的通讯之Feign的使用
声明式的REST客户端（伪RPC）,采用了接口加注解的方式，
在两个服务之间，如我在订单模块需要加载，商品模块的接口，则需要在订单模块添加如下配置，和步骤


1.第一步添加依赖 有时候可能需要加版本号
```xml
        <!-- feign 的使用 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign</artifactId>
            <version>1.4.7.RELEASE</version>
        </dependency>
```
2.在订单模块的启动类上添加注解  @EnableFeignClients  

3.创建客户端，使用注解的方式来调用product里面的方法，如下是具体写法
```java
//调用 LEARN-PRODUCT 服务的里面的接口
@FeignClient(name = "LEARN-PRODUCT")
@RequestMapping("/ProductServer")
public interface ProductClinet {

    /**
     * 调用商品下面的 什么方法 是按照url来定义的 方法名称 可以 不和那边保存一致
     * @return
     */
    @GetMapping("/getMsg")
    public String getMsgPro();
}
```
4.在方法里面调用远程的请求 控制器层面 Autowired 注入上面的注解类，进行远程方法调用
```java
@RestController
@RequestMapping("/order")
public class OrderClinetController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 调用的 feign Clinet 进行通讯
     */
    @Autowired
    public ProductClinet productClinet;

    /**
     * 测试使用 Feign 来调用客户端通讯
     * @return
     */
    @GetMapping("/testFeign")
    public String testFeign(){
        String msgPro = productClinet.getMsgPro();
        return msgPro+"这是使用的Feign方式";
    }
}
```
### 序列化和反序列化
主要DTO添加了有参数的构造方法后，默认的无参数的构造方法将不再提供，所以至少要写两个构造方法，不然就会出错
