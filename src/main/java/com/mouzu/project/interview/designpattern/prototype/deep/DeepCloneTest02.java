package com.mouzu.project.interview.designpattern.prototype.deep;


import org.apache.commons.lang3.SerializationUtils;

/**
 * 深度克隆两种实现方式
 * 1、继承克隆接口，重写克隆方法，对引用类型对象进行克隆
 * 2、实现序列化,需要使用到IO，影响到性能
 */
public class DeepCloneTest02 {
    public static void main(String[] args) throws CloneNotSupportedException{
        Staff02 staff = new Staff02("小帅", 18);
        Company02 company = new Company02("美的公司", staff);
        Company02 cloneCompany = SerializationUtils.clone(company);
        System.out.println("Company02:"+company);
        System.out.println("cloneCompany:"+cloneCompany);
        System.out.println("=====================");
        company.getStaff().setName("小红");
        System.out.println("Company02:"+company);
        System.out.println("cloneCompany:"+cloneCompany);
    }
}
