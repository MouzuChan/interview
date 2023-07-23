package com.mouzu.project.interview.designpattern.proxy.statictend;

public class OverseasHttpServer implements HttpServer{
    @Override
    public void handleRequest() {
        System.out.println("海外HTTP处理器处理请求并返回结果...");
    }
}
