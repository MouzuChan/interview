package com.mouzu.project.interview.spring;

public class SpringBootInfo {
    /**
     * -》什么是 Spring Boot？
     * Spring Boot 是 Spring 开源组织下的子项目，是 Spring 组件一站式解决方案，主要是简化了使用
     * Spring 的难度，简省了繁重的配置，提供了各种启动器，使开发者能快速上手
     *
     * -》SpringBoot与SpringCloud 区别
     * SpringBoot是快速开发的Spring框架，SpringCloud是完整的微服务框架，SpringCloud依赖于SpringBoot
     *
     * -》Spring Boot 的核心注解是哪个？它主要由哪几个注解组成的？
     * 启动类上面的注解是@SpringBootApplication，它也是 Spring Boot 的核心注解，，主要组合包含了以下 3 个注解：
     * @SpringBootConfiguration：组合了 @Configuration 注解，实现配置文件的功能
     * @EnableAutoConfiguration：打开自动配置的功能，也可以关闭某个自动配置的选项， 例
     * 如： java 如关闭数据源自动配置功能： @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
     * @ComponentScan：Spring组件扫描
     *
     * -》SpringBoot Starter的工作原理
     * 在sprinBoot启动时由@SpringBootApplication注解会自动去maven中读取每个starter中的
     * spring.factories文件,该文件里配置了所有需要被创建spring容器中的bean，并且进行自动配置把
     * bean注入SpringContext中
     *
     * -》springboot自动装配原理
     * 主要是Spring Boot的启动类上的核心注解SpringBootApplication注解主配置类，有了这个主配置
     * 类启动时就会为SpringBoot开启一个@EnableAutoConfiguration注解自动配置功能
     * 有了这个EnableAutoConfiguration的话就会：
     * 1、从配置文件META_INF/Spring.factories加载可能用到的自动配置类
     * 2、去重，并将exclude和excludeName属性携带的类排除
     * 3、过滤，将满足条件（@Conditional）的自动配置类返回
     *
     * -》springboot开启事务
     * 1、使用注解EnableTransactionManagement开启事物
     * 2、Service方法上添加注解Transactional便可
     *
     * -》统一异常处理
     * 注解类上@ControllerAdvice+方法上@ExceptionHandler(value=Exception.class)
     */
}
