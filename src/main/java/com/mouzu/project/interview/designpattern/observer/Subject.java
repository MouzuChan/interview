package com.mouzu.project.interview.designpattern.observer;

// 抽象主题类
public interface Subject {
    // 增加订阅者
    void attach(Observer observer);

    // 删除订阅者
    void remove(Observer observer);

    //通知订阅者更新消息
    void notify(String msg);

}
