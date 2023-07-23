package com.mouzu.project.interview.java.thread;

public class ThreadStateWait {
    public static void main(String[] args) throws InterruptedException{
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println("t1将wait1000L");
                    object.wait(3000L);
                    System.out.println("t1将wait");
                    object.wait();
                    System.out.println("t1将执行完");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(1000L);
        synchronized (object){
            System.out.println("t1的状态：" + t1.getState());//TIMED_WAITING
            object.notify();
            Thread.sleep(1000L);
            System.out.println("t1的状态：" + t1.getState());// BLOCKED
        }
        Thread.sleep(3000L);
        System.out.println("t1的状态：" + t1.getState());//WAITING
        Thread.sleep(1000L);
        synchronized (object){
            object.notify();
        }
        System.out.println("t1的状态："+t1.getState());//RUNNABLE
        Thread.sleep(1000L);
        System.out.println("t1的状态："+t1.getState());//TERMINATED
    }
}
