package com.mouzu.project.interview.designpattern.builder;

public class TestBuilder {
    public static void main(String[] args) {
        Director director = new Director();
        Product product = director.build(new Worker());
        System.out.println(product.toString());
    }
}
