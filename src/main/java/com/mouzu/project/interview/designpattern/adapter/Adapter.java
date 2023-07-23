package com.mouzu.project.interview.designpattern.adapter;

// 适配器

/**
 * 适配器：将一个类的接口转换成客户希望的另外一个接口，适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * 适配器实现方法：
 * 1、继承（类适配器，单继承，有局限性）
 * 2、组合（对象适配器，常用）
 */
public class Adapter extends Adaptee implements NetToUsb{
    @Override
    public void handleRequest() {
        super.request();
    }
}
