package com.mouzu.project.interview.designpattern.bridge;

/**
 * 桥接模式：将抽象部分和实现部分分离，使它们可以独立的变化
 * 优点：
 *     1、比多继承方案更好的解决方法，极大的减少了子类的个数，降低管理和维护的成本
 *     2、提高了系统的可扩展性，在多个维度中任意扩展一个维度，不需修改原有逻辑，符合开闭原则
 *
 * 适用场景：
 *     1、JDBC驱动程序
 */
public class BridgeTest {
    public static void main(String[] args) {
        // 苹果笔记本
        Computer apple = new Laptop(new Apple());
        apple.info();
        // 联想台式机
        Computer desktop = new Desktop(new Lenovo());
        desktop.info();
    }
}
