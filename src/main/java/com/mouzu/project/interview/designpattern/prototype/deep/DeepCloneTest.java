package com.mouzu.project.interview.designpattern.prototype.deep;


/**
 * 深度克隆两种实现方式
 * 1、继承克隆接口，重写克隆方法，对引用类型对象进行克隆
 * 2、实现序列化,需要使用到IO，影响到性能
 */
public class DeepCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException{
        Staff staff = new Staff("小帅", 18);
        Company company = new Company("美的公司", staff);
        Company cloneCompany = (Company)company.clone();
        System.out.println("company:"+company);
        System.out.println("cloneCompany:"+cloneCompany);
        System.out.println("=====================");
        company.getStaff().setName("小红");
        System.out.println("company:"+company);
        System.out.println("cloneCompany:"+cloneCompany);
    }
}
