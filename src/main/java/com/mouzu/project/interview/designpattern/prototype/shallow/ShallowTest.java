package com.mouzu.project.interview.designpattern.prototype.shallow;

/**
 * 浅克隆
 */
public class ShallowTest {
    public static void main(String[] args) throws CloneNotSupportedException{
        Student student = new Student(8, "11111");
        School school = new School("建安小学", student);
        School cloneSchool = school.clone();
        System.out.println("school: "+school);
        System.out.println("cloneSchool: "+cloneSchool);
        System.out.println("==========================");

        // 改了被克隆对象的属性student后,被克隆对象和克隆对象的属性值都改
        student.setAge(10);
        System.out.println("school: "+school);
        System.out.println("cloneSchool: "+cloneSchool);

    }
}
