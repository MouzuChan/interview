package com.mouzu.project.interview.java.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInfo {

    /**
     * 谈谈对AQS的理解
     * AQS抽象队列同步器，它是JUC包下Lock类的底层实现，它的核心思想是如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，
     * 并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制。本质是排他锁和共享锁功能，
     * 排他锁即多个线程争取锁时只有一个线程可以获取，比如ReentrantLock，共享锁即同一时刻允许多个线程获取锁资源，比如CountDownLatch和
     * Semaphore。
     */

    private static Lock lock = new ReentrantLock();

    private static int count = 0;

    private static void inrc() {
        try {
            // 加锁
            lock.lock();
            Thread.sleep(10);
            count++;
            // 模拟重入锁
            desc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void desc() {
        lock.lock();
        count--;
        lock.unlock();
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                inrc();
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("count:" + count);
    }
}
