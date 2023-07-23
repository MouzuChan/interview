package com.mouzu.project.interview.designpattern.factory.simple;

import com.mouzu.project.interview.designpattern.factory.Car;
import com.mouzu.project.interview.designpattern.factory.Tesla;
import com.mouzu.project.interview.designpattern.factory.WuLing;

/**
 * 工厂模式作用
 * 定义一个创建对象的接口，将对象的创建和本身的业务逻辑分离，降低系统的耦合度，使得两个修改起来相对容易些，当以后实现改变时，只需要修改工厂类即可
 * 1、解耦：把对象的创建和使用的过程分开
 * 2、降低代码重复
 */
// 静态工厂模式
// 不符合开闭原则 对扩展开放，对修改关闭
public class SimpleFactory {
    public static Car createCar(String name) {
        if ("WuLing".equals(name)) {
            return new WuLing();
        } else if ("Tesla".equals(name)) {
            return new Tesla();
        } else {
            return null;
        }
    }
}
