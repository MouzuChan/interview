package com.mouzu.project.interview.designpattern.proxy.dynamics.jdkmode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Object obj;

    public ProxyHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preRequest();
        Object o = method.invoke(obj, args);
        afterRequest();
        return o;
    }

    public void preRequest(){
        System.out.println("拦截客户端的Http请求，发送至海外的代理服务器，由其向海外http服务器发送请求...");
    }

    public void afterRequest(){
        System.out.println("海外的代理服务器接收海外http服务器反馈结果，发送回客户端浏览器进行显示...");
    }


}
