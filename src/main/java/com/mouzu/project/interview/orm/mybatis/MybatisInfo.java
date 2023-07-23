package com.mouzu.project.interview.orm.mybatis;

public class MybatisInfo {
    /**
     * -》什么是Mybatis？
     * Mybatis是一个半ORM（对象关系映射）框架，它内部封装了JDBC，加载驱动、创建连接、创建statement等繁杂的过程，
     * 开发者开发时只需要关注如何编写SQL语句，可以严格控制sql执行性能，灵活度高
     *
     * 优点：
     *      1、消除了JDBC大量冗余的代码，不需要手动开关连接；
     *      2、基于SQL语句编程，相当灵活，不会对应用程序或者数据库的现有设计造成任何影响，SQL写在XML里，解除sql与程序代码的耦合，便于统一管理；
     *      提供XML标签，支持编写动态SQL语句，并可重用
     * 缺点：
     *      1、SQL语句的编写工作量较大，尤其当字段多、关联表多时，对开发人员编写SQL语句的功底有一定要求
     *      2、SQL语句依赖于数据库，导致数据库移植性差，不能随意更换数据库
     *
     * -》#{}和${}的区别是什么？
     * ${}是字符串替换，#{}是预处理；使用#{}可以有效的防止SQL注入，提高系统安全性
     * Mybatis在处理${}时，就是把${}直接替换成变量的值。而Mybatis在处理#{}时，会对sql语句进行预处理，将sql中的#{}替换为?号，
     * 调用PreparedStatement的set方法来赋值；
     *
     * -》Mapper接口原理
     * JDK动态代理，Mybatis运行时会使用JDK动态代理为Mapper接口生成代理对象 MappedProxy，代理对象会拦截接口方法，根据类的全限定名+方法名，
     * 唯一定位到一个MapperStatement并调用执行器执行所代表的sql，然后将sql执行结果返回。
     *
     * -》Mybatis的Xml映射文件中，不同的Xml映射文件，id是否可以重复？
     *  不同的Xml映射文件，如果配置了namespace，那么id可以重复；如果没有配置namespace，那么id不能重复；原因就是namespace+id是作为Map
     *  的key使用的，如果没有namespace，就剩下id，那么，id重复会导致数据互相覆盖。有了namespace，自然id就可以重复，namespace不同，
     *  namespace+id自然也就不同
     *
     *  -》Mybatis是如何进行分页的？分页插件的原理是什么？
     *  Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页。
     *  可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页
     *
     *  -》Mybatis缓存机制
     *  1、一级缓存是sqlSession级别的缓存，也是本地缓存。每个用户去查询数据的时候都是用sqlSession来执行，为了避免每次查询都是查询数据库，
     *  mybatis把查询出来的数据缓存在sqlSession本地缓存里，后续的sql如果命中缓存则直接从缓存里读取数据；
     *  2、二级缓存是跨sqlSession级别实现，多个用户去查询数据，只要其中一个用户查询数据放到二级缓存里，其他用户就可以从二级缓存加载
     *
     *  -》mybatis的动态标签
     *  srt、resultmap、where、if、foreach、include
     *
     *  -》mybatis和mybatis-plus的区别
     *  mybatis-plus 是 mybatis 的增强工具，它在 mybatis 的基础上又添加了许多的功能，mybatis-plus 是为简化开发，提高效率而生
     *  mybatis：
     *      1、所有SQL语句全部自己写
     *      2、手动解析实体关系映射转换为MyBatis内部对象注入容器
     *      3、不支持Lambda形式调用
     *  mybatis-plus:
     *      1、内置的Mapper,通用的Service,少量配置即可实现单表大部分CRUD操作
     *      2、自动解析实体关系映射转换为MyBatis内部对象注入容器
     *      3、支持Lambda形式调用
     */
}
