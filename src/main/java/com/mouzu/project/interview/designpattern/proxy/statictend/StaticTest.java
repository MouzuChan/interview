package com.mouzu.project.interview.designpattern.proxy.statictend;

/**
 * 静态代理模式：代理类同被代理类一样，都实现同样的接口，以便对外提供同样的服务
 *
 */
public class StaticTest {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpProxy(new OverseasHttpServer());
        httpServer.handleRequest();
    }
}
