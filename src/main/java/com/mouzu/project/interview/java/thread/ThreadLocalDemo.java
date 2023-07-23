package com.mouzu.project.interview.java.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 两大使用场景:
 * 1、每个线程需要一个独享的对象（通常是工具类，典型需要使用的类有SimpleDateFormat和Random，线程不安全）
 * 2、每个线程内需要保存全局变量（例如在拦截器中获取用户信息），可以让不同方法直接使用，避免参数传递的麻烦。
 *
 * 原理：
 *  每个线程独有的ThreadLocalMap。再以ThreadLocal的当前实例，拿到Map中的对应的Entry，从Entry中获取变量的值并返回出去
 */
public class ThreadLocalDemo {
    //创建单例实例 1 每个线程需要一个独享的对象
    static ThreadLocal<DateFormat> sdf = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        //创建多个线程2
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                try {
                    //单例解析 3
                    System.out.println(sdf.get().parse("2020-9-9 11:11:11"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    //清除，避免内存泄露
                    sdf.remove();
                }
                //线程启动 5
            }).start();
        }

        // 当前用户信息需要被线程内所有方法共享
        new Service1().process("");

    }
}

class Service1 {

    public void process(String name) {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();


}

class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}