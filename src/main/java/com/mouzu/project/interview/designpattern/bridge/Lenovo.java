package com.mouzu.project.interview.designpattern.bridge;

public class Lenovo implements Brand{
    @Override
    public void info() {
        System.out.println("联想");
    }
}
