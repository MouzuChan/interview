package com.mouzu.project.interview.designpattern.factory;

import com.mouzu.project.interview.designpattern.factory.simple.SimpleFactory;

public class FactoryTest {
    public static void main(String[] args) {
        // 直接new
//        Car car = new WuLing();
        // 简单工厂模式, 新实现Car类得修改SimpleFactory逻辑，麻烦
        Car wuLing = SimpleFactory.createCar("WuLing");
    }
}
