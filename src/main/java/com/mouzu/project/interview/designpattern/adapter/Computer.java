package com.mouzu.project.interview.designpattern.adapter;

// 客户端类 电脑（想连接网络但无网络端口）
public class Computer {
    public void net(NetToUsb adapter){
        // 需要一个转接头
        adapter.handleRequest();
    }

    public static void main(String[] args) {
        // 继承
        Computer computer = new Computer();
        NetToUsb netToUsb = new Adapter();
        computer.net(netToUsb);

        System.out.println("===============");
        // 组合
        Adaptee adaptee = new Adaptee();
        NetToUsb netToUsb2 = new Adapter2(adaptee);
        computer.net(netToUsb2);
    }

}
