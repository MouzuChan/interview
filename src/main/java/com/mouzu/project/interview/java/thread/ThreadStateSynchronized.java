package com.mouzu.project.interview.java.thread;

public class ThreadStateSynchronized {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (ThreadStateSynchronized.class) {
                System.out.println("t1抢到锁");
            }
        });
        synchronized (ThreadStateSynchronized.class) {
            t1.start();
            Thread.sleep(1000L);
            System.out.println("t1抢不到锁的状态：" + t1.getState());// BLOCKED
        }
    }
}
