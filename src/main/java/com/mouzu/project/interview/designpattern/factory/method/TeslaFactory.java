package com.mouzu.project.interview.designpattern.factory.method;

import com.mouzu.project.interview.designpattern.factory.Car;
import com.mouzu.project.interview.designpattern.factory.Tesla;

public class TeslaFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new Tesla();
    }
}
