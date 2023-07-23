package com.mouzu.project.interview.designpattern.proxy.dynamics.cglibmode;

public class NikeCloth {
    public void makeCloth(String type){
        System.out.println("制作服装：" + type);
    }

    public String brand(){
        return "耐克品牌";
    }

}
