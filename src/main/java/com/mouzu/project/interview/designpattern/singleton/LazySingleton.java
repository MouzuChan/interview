package com.mouzu.project.interview.designpattern.singleton;

// 双重校验锁懒汉式单例
public class LazySingleton {
    // volatile 禁止指令重排序,1、创建对象 2、分配内存 3、将内存地址指向对象
    // 132指令重排序会导致对象实例为空
    private volatile Person person;

    private LazySingleton() {
    }

    public Person getInstance() {
        if (person == null) {
            synchronized (this) {
                // 此次非空判断是为了防止并发重复创建实例子
                if (person == null) {
                    person = new Person();
                }
            }
        }
        return person;
    }


}
