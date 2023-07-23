package com.mouzu.project.interview.java.thread;

public class ThreadStateSleep {
    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                while (running) {
                    System.out.println("t1 running is false,t1将sleep");
                    Thread.sleep(10000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("new t1,t1的状态：" + t1.getState());//NEW
        t1.start();
        Thread.sleep(2000L);
        running = false;
        Thread.sleep(2000L);
        System.out.println("t1.sleep()的状态：" + t1.getState());//TIMED_WAITING
    }


}
