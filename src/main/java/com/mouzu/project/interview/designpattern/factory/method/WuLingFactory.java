package com.mouzu.project.interview.designpattern.factory.method;

import com.mouzu.project.interview.designpattern.factory.Car;
import com.mouzu.project.interview.designpattern.factory.WuLing;

public class WuLingFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new WuLing();
    }
}
