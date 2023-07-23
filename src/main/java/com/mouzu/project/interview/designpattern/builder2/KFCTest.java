package com.mouzu.project.interview.designpattern.builder2;

public class KFCTest {
    public static void main(String[] args) {
        KFCWorker kfcWorker = new KFCWorker();
        // 链式编程
        KFCProduct product = kfcWorker.buildEggTart().buildSprint().getProduct();
        System.out.println(product.toString());
    }
}
