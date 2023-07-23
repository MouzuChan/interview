package com.mouzu.project.interview.designpattern.singleton;

// 饿汉式单例
public class HungrySingleton {
    private static Person person = new Person();

    private HungrySingleton(){}

    public Person getInstance(){
        return person;
    }
}
