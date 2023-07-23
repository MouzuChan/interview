package com.mouzu.project.interview.designpattern.bridge;

public class Apple implements Brand{
    @Override
    public void info() {
        System.out.println("苹果");
    }
}
