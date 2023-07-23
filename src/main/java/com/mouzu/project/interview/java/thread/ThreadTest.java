package com.mouzu.project.interview.java.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

/**
 * 线程创建的几种方式
 * 1、new一个Thread
 * 2、继承Runnable
 * 3、使用Callalbe
 * 4、使用线程池
 */
public class ThreadTest {
    public static void main(String[] args) {
        // 1、new一个Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理逻辑
            }
        }).start();
        // 2、继承Runnable
        new Thread(new UseRunnable()).start();
        // 3、使用Callalbe
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new UseCallable());
        new Thread(integerFutureTask).start();

        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行");
        },"t1");

        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行");
        },"t2");

        Thread t3 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行");
        },"t3");


        CompletableFuture.runAsync(()->t1.start()).thenRun(()->t2.start()).thenRun(()->t3.start());
    }
    /**
     * java线程状态
     * 1、NEW 初始状态
     * 2、RUNNABLE 运行状态
     * 3、BLOCKED 阻塞状态
     * 4、WAITING 等待状态
     * 5、TIMED_WAITING 超时等待状态
     * 6、TERMINATED 终止状态
     *
     * 谈谈对JMM的理解
     * JMM即java内存模型，所有变量都在主存中，主存是线程的共享区域，每个线程都有自己独有的工作内存，
     * 线程想要操作变量必须从主存中copy一份到自己的工作区域，每个独立内存区域相互隔离.
     * 在java多线程并发的环境中，为了保障多个线程能够有效安全的执行，由三个特性组成
     * 原子性、可见性、有序性
     * 1、原子性：一次或多次操作在执行期间不被其他线程影响（synchronized）
     * 2、可见性：当一个线程在工作内存修改了变量，其他线程能立刻知道(volatile)
     * 3、有序性：JVM对指令的优化会让指令执行顺序改变，有序性是禁止指令重排序(volatile)
     *
     * 谈谈对线程安全的理解？
     * 多线程访问某个方法或对象时，不管线程如何交替执行，在程序中也不做任何同步的干预，这个方法或对象都能按预期的结果返回，那么这个方法或对象
     * 我们可以认为是线程安全的，线程安全的表现有三个方面：原子性、可见性、有序性。原子性：一次或多次操作在执行期间不被其他线程影响，
     * CPU的上下文切换是影响线程原子性的原因；
     *
     * 如何在不加锁的情况下对实现线程安全？
     * 1、回答对线程安全的理解
     * 2、一般用Synchronized或Lock实现线程安全
     * 3、不加锁情况下可以用自旋锁、乐观锁或者避免对共享对象的使用，从业务隔离
     *
     * -》线程池拒绝策略
     * 1、AbortPolicy - 抛出异常，中止任务。抛出拒绝执行 RejectedExecutionException 异常信息。线程池默认的拒绝策略
     * 2、CallerRunsPolicy - 使用调用线程执行任务
     * 3、DiscardPolicy - 直接丢弃，其他啥都没有
     * 4、DiscardOldestPolicy - 丢弃队列最老任务，添加新任务
     *
     * -》线程池核心线程数设置参考
     * IO密集型：设置线程数为2*N
     * CPU密集型：线程数为N+1
     *
     * -》多个线程如何保障顺序执行
     * 1、使用join，比如t1\t2\t3三个线程
     *    在t2线程里加入t1.join,在t3线程里加入t2.join
     *
     * 2、使用CompletableFuture.runAsync().thenRun().thenRUN()方法
     *
     * -》守护线程的理解
     * java提供两种类型的线程：用户线程和守护线程
     * 1、用户线程是高优先级线程，jvm在终止任务之前等待所有用户线程完成其任务，守护线程是低优先级线程，其作用是为用户线程提供服务
     * 2、守护线程最典型的应用就是GC
     *
     */




}
