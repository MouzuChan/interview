package com.mouzu.project.interview.designpattern.proxy.dynamics.cglibmode;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {
    private Object obj;

    public ProxyFactory(Object obj){
        this.obj = obj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("makeCloth")) {
            System.out.println("制作服装前的准备...");
        }
        Object invoke = method.invoke(obj, objects);
        if (method.getName().equals("makeCloth")) {
            System.out.println("制作服装后的处理...");
        }
        return invoke;
    }
}
