package com.mouzu.project.interview.designpattern.proxy.dynamics.cglibmode;

import org.springframework.cglib.proxy.Enhancer;

/**
 * CGLIB动态代理
 */
public class CglibTest {
    public static void main(String[] args) {
        NikeCloth nikeCloth = new NikeCloth();
        ProxyFactory proxyFactory = new ProxyFactory(nikeCloth);
        // 创建Enhancer对象，类似JDK动态代理的Proxy类，用来生成代理对象
        Enhancer enhancer = new Enhancer();
        // 设置字节码对象，CGLIB根据字节码生成目标类的子类
        enhancer.setSuperclass(nikeCloth.getClass());
        // 设置回调函数
        enhancer.setCallback(proxyFactory);
        // 返回代理对象
        NikeCloth nikeClothProxy = (NikeCloth) enhancer.create();
        nikeClothProxy.makeCloth("球服");
        System.out.println(nikeClothProxy.brand());

    }
}
