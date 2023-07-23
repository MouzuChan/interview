package com.mouzu.project.interview.designpattern.prototype.shallow;

public class Student {
    private int age;

    private String id;

    public Student(int age, String id){
        this.age = age;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
