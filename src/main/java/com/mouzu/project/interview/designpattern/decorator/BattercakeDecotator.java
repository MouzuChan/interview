package com.mouzu.project.interview.designpattern.decorator;

public abstract class BattercakeDecotator extends Battercake{
    private Battercake battercake;
    public BattercakeDecotator(Battercake battercake){
        this.battercake = battercake;
    }
    protected abstract void doSomething();

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }
}
