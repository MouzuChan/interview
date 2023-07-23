package com.mouzu.project.interview.designpattern.prototype.shallow;

/**
 * 1、实现克隆接口
 * 2、重写clone方法
 */
public class School implements Cloneable{
    private String name;

    private Student student;

    public School(String name, Student student){
        this.name = name;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    protected School clone() throws CloneNotSupportedException {
        return (School) super.clone();
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", student=" + student +
                '}';
    }
}
