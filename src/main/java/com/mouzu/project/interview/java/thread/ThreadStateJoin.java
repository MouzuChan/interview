package com.mouzu.project.interview.java.thread;

public class ThreadStateJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2中执行t1.join(5000L)");
                t1.join(5000L); // t2等待t1 5s
                System.out.println("t2中执行t1.join()");
                t1.join(); // t2等待t1执行完
                System.out.println("t2执行完");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000L);
        System.out.println("t2的状态：" + t2.getState()); //TIMED_WAITING
        Thread.sleep(5000L);
        System.out.println("t2的状态：" + t2.getState());//WAITING
    }
}
