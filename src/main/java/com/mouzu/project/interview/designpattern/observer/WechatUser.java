package com.mouzu.project.interview.designpattern.observer;

public class WechatUser implements Observer{
    private String name;

    public WechatUser(String name) {
        this.name = name;
    }

    @Override
    public void updateMsg(String msg) {
        System.out.println(name + "-" + msg);
    }
}
