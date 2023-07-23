package com.mouzu.project.interview.designpattern.builder;

/**
 * 建造者模式
 * 优点：
 *      1、产品的建造和表示分离，实现了解耦，客户端不必知道产品内部实现的细节
 */
public abstract class Builder {
    abstract void buildA();//地基

    abstract void buildB();//钢筋工程

    abstract void buildC();//铺底线

    abstract void buildD();//粉刷

    abstract Product getProduct();//完工，得到产品
}
