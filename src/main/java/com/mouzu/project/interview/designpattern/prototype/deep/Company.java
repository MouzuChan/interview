package com.mouzu.project.interview.designpattern.prototype.deep;

import java.io.Serializable;

public class Company implements Cloneable{
    private String companyName;

    private Staff staff;

    public Company(String companyName, Staff staff){
        this.companyName = companyName;
        this.staff = staff;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Company company = (Company)clone;
        //将对象属性也进行克隆
        company.setStaff((Staff) this.staff.clone()); ;
        return clone;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", staff=" + staff +
                '}';
    }
}
