package com.mouzu.project.interview.designpattern.bridge;

public abstract class Computer {
    // 组合
    protected Brand brand;

    public Computer(Brand brand){
        this.brand = brand;
    }

    public void info(){
        // 输出电脑品牌
        brand.info();
    }

}
