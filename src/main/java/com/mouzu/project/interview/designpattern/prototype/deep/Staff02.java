package com.mouzu.project.interview.designpattern.prototype.deep;

import java.io.Serializable;

public class Staff02 implements Serializable {
    private static final long serialVersionUID = -8021933711868383132L;

    private String name;

    private int age;

    public Staff02(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
