package com.mouzu.project.interview.spring;

public class SpringInfo {

    /**
     * -》spring是什么
     *  spring是J2EE应用程序框架，核心技术是IOC、AOP，主要是针对javaBean的生命周期进行管理的轻量级容器
     *
     * -》spring框架好处：
     *  1、IOC实现方便解耦，简化开发
     *  2、AOP实现面向切面的编程
     *  3、轻量级框架没有侵入性
     *
     * -》spring的核心模块
     *  1、spring core:核心容器
     *      核心容器提供spring框架的基本功能，主要实现控制反转IoC和依赖注入DI、Bean配置以及加载
     *  2、Spring AOP：Spring面向切面编程
     *  3、Spring context：Spring上下文
     *      Spring上下文是一个配置文件，向Spring框架提供上下文信息
     *  4、Spring Dao
     *      DAO模块主要目的是将持久层相关问题与一般的的业务规则和工作流隔离开来，通过对JDBC进行了再封装，隐藏了Connection、Statement、
     *      ResultSet等JDBC API，使DAO模块直接继承JdbcDaoSupport类
     *  5、Spring ORM（Object Relation Mapper）对象关系映射模块
     *  6、Spring Web模块
     *      Web模块建立在应用程序上下文模块之上，为基于Web的应用程序提供了上下文。Web层使用Web层框架，可选的，可以是Spring自己的MVC框架，
     *      或者提供的Web框架，如Struts、Webwork、tapestry和jsf
     *  7、Spring MVC
     *
     *  -》Spring MVC 的工作流程
     *  1、客户端发送请求，请求到达 DispatcherServlet 主控制器
     *  2、DispatcherServlet 控制器调用 HandlerMapping 处理
     *  3、HandlerMapping 负责维护请求和 Controller 组件对应关系。 HandlerMapping 根据请求调用对应的 Controller 组件处理
     *  4、Controller 业务方法处理完毕后，会返回一个 ModelAndView 对象。该组件封装了模型数据和视图标识
     *  5、DispatcherServlet 主控制器调用 ViewResolver 组件，根据 ModelAndView 信息处理。定位视图资源，生成视图响应信息
     *  6、控制器将响应信息给用户输出
     *
     * -》beanFactory的理解
     * beanFactory会在bean的生命周期的各个阶段中对bean进行各种管理，并且spring将这些阶段通过各种接口暴露给我们，让我们可以对bean
     * 进行各种处理，我们只要让bean实现对应的接口，那么spring就会在bean的生命周期调用我们实现的接口来处理该bean
     *
     * -》什么是依赖注入？实现依赖注入的方式有哪些
     * 依赖注入是指在 Spring IOC 容器创建对象的过程中，将所依赖的对象通过配置进行注入。我们可以通过依赖注入的方式来降低对象间的耦合度
     * 1、set方式注入
     * 2、通过构造方法注入
     * 3、自动注入，如@Autowire\@Resource
     *
     * -》@Autowire和@Resource区别
     * 1、Autowire是Spring框架提供的，Resource是java自带的
     * 2、Autowire根据类型匹配，@Resource根据名称
     *
     * -》spring 中的 IOC 容器有哪些?有什么区别
     * spring 主要提供了「两种 IOC 容器」，一种是 「BeanFactory」，还有一种是 「ApplicationContext」，它们的区别就在于，BeanFactory
     * 「只提供了最基本的实例化对象和拿对象的功能」，而 ApplicationContext 是继承了 BeanFactory 所派生出来的产物，
     * 是其子类，它的作用更加的强大，比如支持注解注入、国际化、bean自动装配等功能
     *
     * -》factoryBean的作用
     * 一个创建或修饰其他对象的工厂bean,跟设计模式中工厂模式或装饰器模式相似，可以创建除自身以外的其他对象
     *
     * -》怎么获取ApplicationContext对象
     *  1、springboot中用@Autowired获取
     *  2、通过spring工厂获取
     *      //applicationContext.xml 在 src下
     *      ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
     *      //applicationContext.xml 在WEB-INF下
     *      ApplicationContext ac = new FileSystemXmlApplicationContext("applicationContext.xml");
     *      //容器获取
     *      ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
     *
     *      ApplicationContext直译为应用上下文，是用来加载Spring框架配置文件，来构建Spring的工厂对象，它也称之为Spring容器的上下文对象，
     *      也称之为Spring的容器
     *
     * spring ioc
     * 控制反转
     *
     * -》spring aop
     *  一般称为面向切面编程，用于将那些与业务无关，但却对多个对象产生影响的公共行为和逻辑，抽取并封装为一个可重用的模块，
     *  这个模块被命名为“切面”（Aspect），减少系统中的重复代码，降低了模块间的耦合度，同时提高了系统的可维护性
     *  核心原理：观察所调用的方法是否符合切入点表达式，如果符合，则使用代理执行增强方法
     *
     * -》AOP的相关概念
     * 1、切面（Aspect）： 在Spring Aop指定就是“切面类” ，切面类会管理着切点、通知。
     * 2、连接点（Join point）： 连接点是在应用执行过程中能够插入切面（Aspect）的一个点。这些点可以是调用方法时，抛出异常时。
     * 3、通知（Advice）： 就是需要增加到业务方法中的公共代码， 通知有很多种类型分别可以在需要增加的业务方法不同位置进行执行
     * （前置通知、后置通知、异常通知、返回通知、环绕通知）
     * 4、切点（Pointcut）： 核心方法， 结合切点表达式进行实现
     * 5、目标对象（Target Object）： 指定是增强的对象
     * 6、织入（Weaving） ： spring aop用的织入方式：动态代理。 就是为目标对象创建动态代理的过程就叫织入
     *
     * 单例bean线程安全问题了解不？
     * 对于单例Bean,所有线程都共享一个单例实例Bean,因此是存在资源的竞争。如果单例Bean,是一个无状态Bean，也就是线程中的操作不会对Bean
     * 的成员执行查询以外的操作，那么这个单例Bean是线程安全的。但是如果Bean是有状态的，那就需要开发人员自己来进行线程安全的保证。方法有下：
     * 1、改变bean的作用域 把 "singleton"改为’‘protopyte’
     * 2、在 Bean 中尽量避免定义可变的成员变量。
     * 3、在类中定义一个 ThreadLocal 成员变量，将需要的可变成员变量保存在 ThreadLocal 中（推荐的一种方式）。
     *
     *
     * bean的生命周期
     * 1、解析xml配置或者注解配置的类，得到BeanDefinition
     * 2、通过BeanDefinition反射创建Bean对象
     * 3、对Bean对象进行属性赋值，包括自定义属性和容器对象属性
     * 其中容器对象属性是回调实现了Aware接口的方法，如BeanNameAware
     * 4、调用BeanPostProcessor的初始化前的方法
     * 5、调用init初始化方法
     * 6、调用BeanPostProcessor的初始化后的方法，此处会进行AOP
     * 7、将创建的Bean放入Map中
     * 8、业务使用Bean对象
     * 9、Spring容器关闭时调用DisposableBean的destory()方法销毁bean
     *
     * -》spring是如何解决循环依赖的问题的？
     * 使用三级缓存去解决循环依赖的，其「核心逻辑就是把实例化和初始化的步骤分开，然后放入缓存中」，供另一个对象调用
     * spring bean的生命周期是由实例化、属性赋值、初始化和销毁组成
     * 「第一级缓存」：用来保存实例化、初始化都完成的对象
     * 「第二级缓存」：用来保存实例化完成，但是未初始化完成的对象
     * 「第三级缓存」：用来保存一个对象工厂，提供一个匿名内部类，用于创建二级缓存中的对象
     *
     * 假设A、B对象存在循环依赖：
     *
     * 1、实例化A：并把获取半成品A对象的BeanFactory，放入Map<String, ObjectFactory<?>> singletonFactories缓存(三级)中(
     * 这里并不是直接将Bean放入缓存，而是包装成ObjectFactory对象再放入)；
     * 2、A属性赋值(阻塞)：发现A依赖B，去一二三级缓存中找B，找不到，则去创建对象B；
     * 3、实例化B：同A，把获取半成品B对象的BeanFactory，放入singletonFactories缓存(三级)中；
     * 4、B属性赋值：发现B依赖A，从三级缓存中通过singletonFactories得到A，然后把对象A放入earlySingletonObjects缓存(二级)中，并删除三级缓存中的对象A；
     * 5、B属性赋值成功：对象B创建完成，把B放入一级缓存，删除三级缓存中的对象B；
     * 6、A属性赋值成功：从一级缓存中得到B，属性赋值成功，删除二级缓存中的A，并将A放入一级缓存。
     *
     *
     * -》事务的传播机制
     * 1、REQUIRED：默认值，支持当前事务，如果没有事务会创建一个新的事务
     * 2、SUPPORTS：支持当前事务，如果没有事务的话以非事务方式执行
     * 3、MANDATORY：支持当前事务，如果没有事务抛出异常
     * 4、REQUIRES_NEW：创建一个新的事务并挂起当前事务
     * 5、NOT_SUPPORTED：以非事务方式执行，如果当前存在事务则将当前事务挂起
     * 6、NEVER：以非事务方式进行，如果存在事务则抛出异常
     * 7、NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与REQUIRED类似的操作
     *
     * -》事务失效的原因
     * 1、数据库引擎不支持事务
     * 2、没有被 Spring 管理
     * 3、方法不是 public 的
     * 4、自身调用问题
     * 5、数据源没有配置事务管理器
     * 6、事务传播级别为不支持事务
     * 7、异常被吃
     * 8、异常类型错误
     * 发生最多就是自身调用、异常被吃、异常抛出类型不对这三个
     *
     * -》为啥自身调用会导致事务失效
     * 因为事务是基于AOP，底层是动态代理，默认只有在外部调用事务经过 Spring 的代理类才会生效。因为自身调用是直接用当前类this调用对应的方法，
     * 所以事务失效。想不要事务失效可以在代码中直接生成对应的代理类，然后调用对应的方法。
     *
     * -》怎么定义类的作用域？
     * 通过scope属性指定类的作用域
     *
     * -》@RequestMapping注解的作用
     * 建立URL请求和请求处理方法之间的关系，可用在类上或方法上
     *
     *-》基于java的spring注解配置
     * 1、 @ Configuration 的注解类表示这个类可以使用 Spring IoC 容器作为 bean 定义的来源
     * 2、@ Bean 注解告诉 Spring，一个带有 @ Bean 的注解方法将返回一个对象，该对象应该被注册为在 Spring 应用程序上下文中的 bean。
     * 3、@ Value：用于修饰一个 Field，用于为该 Field配置一个值，相当于配置一个变量。
     * 4、@ Scope：用于修饰一个方法，指定该方法对应的 Bean的生命域
     * 5、@ Lazy：用于修饰一个方法，指定该方法对应的 Bean是否需要延迟初始化
     * 6、@ DependsOn：用于修饰一个方法，指定在初始化该方法对应的 Bean之前初始化指定的 Bean
     *
     * -》基于注解的容器配置
     * @Qualifier、@Qualifier、@Resouce、@PostConstruct、@PreDestroy
     *
     * -》@Configuration的理解
     * 功能：将想要的组件添加到容器中，配置类
     *
     * -》@Configuration 和 @Component 的区别
     * @Configuration 中所有带 @Bean 注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例；而调用@Component类中的@Bean注解的方法，
     * 返回的是一个新的实例。
     *
     */
}
