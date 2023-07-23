package com.mouzu.project.interview.java.thread;

/**
 * synchronized：保障代码的同步性
 */
public class SynchronizedInfo {
    // 修饰实例方法
    synchronized void method(){}

    //修饰静态方法（当前类）
    synchronized static void staticMethod(){}

    public void add(){
        // 修饰代码块,this锁当前类对象，类.class给定class锁
        synchronized(this){

        }
    }

    /**
     * synchronized底层实现原理：
     * synchronized在jvm中是通过monitor监视器锁来实现的，针对synchronized修饰同步代码块它是通过显示的monitorEnter和monitorExit实现，
     * 针对synchronized实例方法则是ACC_SYNCHRONIZED标识来实现；两者的本质都是对对象监视器 monitor 的获取。
     *
     * JDK1.6 之后的 synchronized 底层做了哪些优化？
     * JDK1.6 对锁的实现引入了大量的优化，如偏向锁、轻量级锁、自旋锁、适应性自旋锁、锁消除、锁粗化等技术来减少锁操作的开销。
     * （背景：1.6之前synchronized是个重量级锁，它底层由monitor实现的，而monitor又依赖操作系统的一些指令，获取锁时需要把线程挂起，
     *      从用户态切换到内核态执行相关指令，这个过程比较消耗性能，1.6之后引进引进偏向锁、轻量级锁、自旋锁等技术，避免每次获取锁都要
     *      切换到内核态，从而达到降低锁操作的开销）
     *
     * synchronized和ReentrantLock的区别：
     * 1、实现方面：一个是JVM层次通过，（监视器锁）monitor实现的。一个是通过JDK（AQS）实现的
     * 2、相应中断不同：ReentrantLock 可以响应中断，解决死锁的问题，而 synchronized 不能响应中断。
     * 3、锁的类型不同：synchronized 是非公平的锁，而ReentrantLock即可以是公平的也可以是非公平的
     * 4、获取锁的方式不同：synchronized 是自动加锁和释放锁的，而 ReentrantLock 需要手动加锁和释放锁。
     *
     * synchronized和Lock的区别
     * 1、功能角度：synchronized和Lock都是解决线程安全问题的工具
     * 2、特性角度：synchronized是java的关键字，Lock是JUC包提供一个接口，该接口有很多实现类，比如ReentrantLock、CountDownLatch等
     *           synchronized可以修饰代码块和方法，可以通过synchronized+锁对象的生命周期控制锁作用范围；Lock的锁粒度是通过Lock()
     *           和unlock()方法决定的，Lock锁作用域取决于Lock实例的生命周期。Lock锁灵活性比synchronized更高
     * 3、锁的类型：synchronized 是非公平的锁，而ReentrantLock即可以是公平的也可以是非公平的，而且Lock的tryLock()方法实现了
     *             非阻塞式竞争锁。synchronized 是自动加锁和释放锁的，而 ReentrantLock 需要手动加锁和释放锁
     * 4、锁的性能：synchronized引进偏向锁、轻量级锁、自旋锁、适应性自旋锁、锁消除、锁粗化等技术实现锁的优化，Lock是通过CLH自旋锁方式实现
     *             锁的优化
     *
     * synchronized 和 volatile 有什么区别？
     * 1、volatile 关键字是线程同步的轻量级实现，所以 volatile性能肯定比synchronized关键字要好 。但是 volatile 关键字只能用于变量
     * 而 synchronized 关键字可以修饰方法以及代码块 。
     * 2、volatile 关键字能保证数据的可见性，但不能保证数据的原子性。synchronized 关键字两者都能保证。
     * 3、volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized 关键字解决的是多个线程之间访问资源的同步性。
     *
     * -》volatile的底层实现原理
     * 内存屏障
     * 1、在每个volatile写操作的前面插入一个StoreStore屏障
     * 2、在每个volatile写操作的后面插入一个StoreLoad屏障
     * 3、StoreStore屏障将保障上面所有的普通写操作结果在volatile写之前会被刷新到主内存->普通写操作对其他线程可见
     * 4、StoreLoad屏障的作用是避免volatile写操作与后面可能有的volatile读/写操作重排序
     */



}
