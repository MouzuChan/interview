package com.mouzu.project.interview.designpattern.prototype.deep;

import java.io.Serializable;

public class Company02 implements Serializable {
    private static final long serialVersionUID = -3331091374922110072L;

    private String companyName;

    private Staff02 staff;

    public Company02(String companyName, Staff02 staff){
        this.companyName = companyName;
        this.staff = staff;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Staff02 getStaff() {
        return staff;
    }

    public void setStaff(Staff02 staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", staff=" + staff +
                '}';
    }
}
