package com.mouzu.project.interview.designpattern.adapter;

// 适配器 组合方法实现
public class Adapter2 implements NetToUsb{

    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void handleRequest() {
        adaptee.request();
    }
}
