package com.mouzu.project.interview.java.multistate.override;

public class OverRideDemo extends Super{
    @Override
    public void info() {
        System.out.println("i am a son");
    }

    public static void main(String[] args) {
        Super superInfo = new Super();
        OverRideDemo son = new OverRideDemo();
        superInfo.info();
        son.info();
    }
}
