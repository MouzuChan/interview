package com.mouzu.project.interview.designpattern.decorator;

public class SausageDecorator extends BattercakeDecotator{
    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }
    @Override
    protected void doSomething() {

    }

    protected String getMsg(){
        return super.getMsg() + "+1根香肠";
    }
    protected int getPrice(){
        return super.getPrice() + 2;
    }

}
