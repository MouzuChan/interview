package com.mouzu.project.interview.spring;

public class SpringCloudInfo {
    /**
     * -》SpringCloud是什么
     * 一套非常完善的分布式框架，利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，
     * 如服务发现注册、配置中心、智能路由、消息总线、负载均衡、断路器、数据监控等
     *
     * -》介绍SpringCloud的核心模块
     * 1、Spring Cloud Eureka：服务注册与发现
     * 2、Spring Cloud Zuul：服务网关
     * 3、Spring Cloud Ribbon：客户端负载均衡
     * 4、Spring Cloud Feign：声明性的Web服务客户端
     * 5、Spring Cloud Hystrix：断路器
     * 6、Spring Cloud Config：分布式统一配置管理
     *
     * -》Eureka是什么?
     * 一个基于 REST 服务的，服务注册与发现的组件，以实现中间层服务器的负载平衡和故障转移
     * 主要包括两个组件：Eureka Server 和 Eureka Client
     * Eureka Client：一个Java客户端，用于简化与 Eureka Server 的交互（通常就是微服务中的客户端和服务端）
     * Eureka Server：提供服务注册和发现的能力（通常就是微服务中的注册中心）
     *
     * 服务在Eureka上注册，然后每隔30秒发送心跳来更新它们的租约。如果客户端不能多次续订租约，那么它将在大约90秒内
     * 从服务器注册表中剔除。注册信息和更新被复制到集群中的所有eureka节点（防止宕机）。来自任何区域的客户端都可以
     * 查找注册表信息（每30秒发生一次）来定位它们的服务（可能在任何区域）
     *
     * -》Eureka的自我保护机制
     * 默认情况下，如果Eureka Server在运行期间会去统计心跳失败的比例,在15分钟之内是否低于85%,如果发现85%以上的服务都没有心跳，
     * Eureka Server会进入自我保护模式，在该模式下Eureka Server会保护服务注册表中的信息，不在删除注册表中的
     * 数据，当网络故障恢复后，Eureka Servic 节点会自动退出自我保护模式
     *
     * -》feign的负载均衡怎么配置
     * 使用@FeignClient注解的时候 是默认使用了ribbon进行客户端的负载均衡的,默认的是随机的策略,那么如果我们想要更改策略的话,
     * 需要修改消费者yml中的配置，有随机、轮询、最空闲连接策略等
     *
     * -》feign服务熔断和降级
     * 服务熔断：一般是某个服务故障或者是异常引起的。熔断机制是应对雪崩效应的一种微服务链路保户机制，当扇出链路的某个微服务不可用或者响应
     * 时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的相应信息。当检测当该节点微服务调用响应正常后恢复调用链路，
     * 熔断的目的是当A服务模块中的某块程序出现故障后为了不影响其他客户端的请求而做出的及时回应。
     *
     * 在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定的阈值，
     * 缺省是5秒内20次调用失败就会启动熔断机制。熔断机制的注解是@HystrixCommand
     *
     * 1、在启动类加@EnableCircuitBreaker
     * 2、在配置文件开启hystrix的开关 feign.hystrix.enabled=true
     * 3、在调用方法上加@HystrixCommand注解
     *
     * 服务降级：服务器当压力剧增的时候，根据当前业务情况及流量，对一些服务和页面进行有策略的降级。
     *          服务降级处理是在客户端实现完成的，与服务端没有关系。
     *
     */
}
