package com.mouzu.project.interview.ratelimit;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Target：用于描述注解的使用范围，该注解可以使用在什么地方
 *      ElementType.TYPE  应用于类、接口（包括注解类型）、枚举
 *      ElementType.FIELD 应用于属性（包括枚举中的常量）
 *      ElementType.METHOD 应用于方法
 *
 * @Retention：表明该注解的生命周期
 *      RetentionPolicy.SOURCE  编译时被丢弃，不包含在类文件中
 *      RetentionPolicy.CLASS   JVM加载时被丢弃，包含在类文件中，默认值
 *      RetentionPolicy.RUNTIME 由JVM 加载，包含在类文件中，在运行时可以被获取到
 *
 * @Inherited：是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的
 *
 * @Documented：表明该注解标记的元素可以被Javadoc 或类似的工具文档化
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiterAnnotation {
    /**
     * 限流值 默认1秒一次请求；<br/>
     * 例如： <br/>
     * setLimiterValue=0.5 这个是配置2秒通过1个请求；<br/>
     * setLimiterValue=2 这个是配置1秒通过2个请求；<br/>
     *
     * @return 限流值
     */
    double setLimiterValue() default 1.0;

    /**
     * 获取超时时间 默认:500
     */
    long timeOut() default 500;

    /**
     * 超时时间单位 默认:毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 无法获取返回提示信息
     */
    String msg() default "亲，服务器快被挤爆了，请稍后再试！";
}
