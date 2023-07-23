package com.mouzu.project.interview.designpattern.builder2;

public abstract class KFCBuilder {
    abstract KFCBuilder buildEggTart();//蛋挞

    abstract KFCBuilder buildSprint();//雪碧

    abstract KFCBuilder buildFriedChicken();//炸鸡

    abstract KFCBuilder buildFamilyBucket();//全家桶

    abstract KFCProduct getProduct();//完工，得到产品
}
