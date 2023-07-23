package com.mouzu.project.interview.designpattern.proxy.statictend;

public class HttpProxy implements HttpServer{
    private HttpServer overseasHttpServer;

    public HttpProxy(HttpServer overseasHttpServer){
        this.overseasHttpServer = overseasHttpServer;
    }

    @Override
    public void handleRequest() {
        preRequest();
        overseasHttpServer.handleRequest();
        afterRequest();
    }

    public void preRequest(){
        System.out.println("拦截客户端的Http请求，发送至海外的代理服务器，由其向海外http服务器发送请求...");
    }

    public void afterRequest(){
        System.out.println("海外的代理服务器接收海外http服务器反馈结果，发送回客户端浏览器进行显示...");
    }
}
