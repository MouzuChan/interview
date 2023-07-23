package com.mouzu.project.interview.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionSubject implements Subject{
    private List<Observer> wechatUsers = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        wechatUsers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        wechatUsers.remove(observer);
    }

    @Override
    public void notify(String msg) {
        for (Observer observer : wechatUsers) {
            observer.updateMsg(msg);
        }
    }
}
