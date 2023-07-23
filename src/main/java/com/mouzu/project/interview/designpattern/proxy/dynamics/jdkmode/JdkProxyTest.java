package com.mouzu.project.interview.designpattern.proxy.dynamics.jdkmode;

import com.mouzu.project.interview.designpattern.proxy.statictend.HttpServer;
import com.mouzu.project.interview.designpattern.proxy.statictend.OverseasHttpServer;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * Java提供了Proxy工具类newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)来生成代理对象
 *
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        HttpServer overseasHttpServer = new OverseasHttpServer();
        ProxyHandler proxyHandler = new ProxyHandler(overseasHttpServer);
        // 生成代理对象
        HttpServer httpServerProxy = (HttpServer) Proxy.newProxyInstance(proxyHandler.getClass().getClassLoader(),
                overseasHttpServer.getClass().getInterfaces(),proxyHandler);
        httpServerProxy.handleRequest();
    }
}
