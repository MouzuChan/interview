package com.mouzu.project.interview.designpattern.decorator;

/**
 * 装饰器模式：只在不改变原有对象的基础上，动态的给一个对象添加一些额外的职责
 * https://blog.csdn.net/J_Newbie/article/details/126829179
 */
public class DecoratorClient {
    public static void main(String[] args) {
        //买一个煎饼
        Battercake battercake;
        battercake = new BaseBattercake();
        //煎饼有点小，想再加1个鸡蛋
        battercake = new EggDecorator(battercake);
        battercake = new EggDecorator(battercake);
        battercake = new SausageDecorator(battercake);
        System.out.println(battercake.getMsg() + ",总价: " + battercake.getPrice());
    }

}
