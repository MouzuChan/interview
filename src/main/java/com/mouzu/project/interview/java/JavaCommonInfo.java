package com.mouzu.project.interview.java;

import java.util.*;

public class JavaCommonInfo {
    /**
     * -》类的finalize方法作用
     * 当垃圾收集确定没有对对象的引用时，由对象上的垃圾收集器调用。子类重写 finalize方法配置系统资源或执行其他清理
     *
     * -》top\ps
     * top命令经常用来监控linux的系统状况，是常用的性能分析工具，能够实时显示系统中各个进程的资源占用情况。
     *ps命令只看到的是命令执行瞬间的进程信息,而top可以持续的监视
     *
     *-》CPU使用率过高排查思路
     * 1、使用top命令，然后按P按照CPU使用率降序排序，找到占用CPU过高的进程id
     * 2、使用`ps -mp [进程id] -o THREAD,tid,time | sort -rm`获取线程信息，并找到占用CPU高的线程
     * 3、使用`echo 'obase=16;[线程id]' | bc`或`echo 'obase=16;[线程id]' | printf "%x\n" [线程id]`将线程ID转为16进制
     * 4、使用`jstack 进程id | grep "线程id的16进制" -A 30`打印线程的堆栈信息
     * 5、从堆栈信息中找到是程序中的那几行代码是一直处于running状态的，从而定位问题。
     *
     * -》分布式锁的实现方式
     * 1、基于mysql数据库
     *  悲观锁：对表中的锁记录根据主键进行一致性锁定读，如果为空，则可以插入一条记录，如果已有记录判断下状态和时间，是否已经超时。
     *      这里需要注意一下哈，必须要加事务哈。
     *  乐观锁：搞个version字段，每次更新修改，都会自增加一，然后去更新时，把查出来的那个版本号，带上条件去更新，如果是上次那个版本号，
     *      就更新，如果不是，表示别人并发修改过了，就继续重试。
     *
     * 2、基于redis
     * 主要基于set key value nx px expireTime 指令去实现
     * 自己实现方案可能存在问题：
     * 1、锁被别的线程误删（用lua脚步保障锁释放的原子性）
     * 2、锁过期释放了，业务还没执行完
     *      把锁的过期时间设置长一些，大于正常业务处理时间长一些，大于正常业务处理时间
     *      开启一个定时守护线程，每隔一段时间检查锁是否还存在，存在则对锁的过期时间延长，防止锁过期提前释放
     * 使用Redisson开源框架解决上述问题
     *
     * 3、基于zookeeper
     *
     * -》对称加密和非对称加密
     * 对称加密：加密和解密使用的是同一个密钥的加密方式
     * 非对称加密：非对称加密指的是根据特殊规则生成两把密钥 A 和 B，分别叫做公钥和私钥。私钥自己保留，公钥则分发给自己的小伙伴用来用来和自己通信
     *
     * -》restful api理解
     * restful：用URL定位资源、用HTTP动词（GET、POST、PUT、DELETE)描述操作，restful api有四大设计原则和规范
     * 1、资源：网络上的一个实体，一段文本，一张图片或者一首歌曲
     * 2、统一接口：RESTful风格的数据元操CRUD，分别对应HTTP方法：GET用来获取资源，POST用来新建资源（也可以用于更新资源），
     * PUT用来更新资源，DELETE用来删除资源，这样就统一了数据操作的接口
     * 3、URI。可以用一个URI（统一资源定位符）指向资源，即每个URI都对应一个特定的资源
     * 4、无状态。所谓无状态即所有的资源都可以URI定位，而且这个定位与其他资源无关，也不会因为其他资源的变化而变化
     *
     * -》数组和集合的区别
     * 1、数组的长度是固定的，集合的长度可以动态扩展
     * 2、数组只能存储相同类型的数据，集合可以存储不同的类型的数据
     *
     * -》静态代理和动态代理的区别
     *  静态代理：代理类和委托类有共同的父类或父接口，需程序员手动编写它的源代码
     *  动态代理：在创建代理对象上更加的灵活，由Java反射机制动态产生
     *
     * -》创造对象的方式
     * 1、直接new
     * 2、利用反射class.forName()获取字节码后newInstance
     * 3、获取类的构造器去newInstance
     * 4、克隆方法，实现Cloneable接口并重写其中的clone方法（浅拷贝）
     * 5、反序列化
     *
     *
     * -》反射的定义：
     * 在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法，对于任意一个对象，都能够调用它的任意一个方法和属性，
     * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制
     *
     * -》为啥要codeReview
     * 1、对于同一段业务代码，由于看待问题的角度不同，评审者可能会比开发者更容易发现其中的问题
     * 2、提高代码质量和可维护性, 可读性等
     * 3、查漏补缺, 发现一些潜在的问题点等。
     * 4、一种知识分享，通过学习与反思，实现自我提升
     *
     * -》codeReview的方向
     * 1、代码可读性，能否非常容易的理解代码逻辑
     * 2、命名
     *      描述是否准确。不建议使用生僻单词
     *      命名格式是否与团队风格一致
     * 3、函数体长度/类长度
     *      函数体太长，不利于阅读。一般建议不超过50行
     *      类太长，有可能违背“单一职责原则
     * 4、参数个数
     *      参数不要太多。一般不超过5个；5个以上建议使用对象。
     * 5、注释
     *      恰到好处的注释，能够帮助评审者更好地理解函数体和类
     *
     * -》泛形的作用
     * 泛型提供了编译时类型的安全检测机制,可以减少我们程序出错的机会
     *
     * -》抽象类和接口的区别
     * 1、抽象类是用abstract,继承使用extends,接口类是用Interface,实现用implements
     * 2、抽象类可以有具体的方法，接口不可以
     * 3、抽象类可以有构造器，接口不可以有
     * 4、接口成员变量默认为public static final，必须赋初值，不能被修改;抽象类中成员变量默认default，可在子类中被重新定义，也可被重新赋值
     *
     * -》实现异步的几种方式
     * 异步编程是通过将耗时的任务分配给其他线程或者线程池来实现的，可以提高程序的并发处理能力，让程序具有更好的性能和响应速度
     * 1、线程异步：通过创建一个新的线程来执行耗时操作，从而避免阻塞主线程
     * 2、Future异步：通过使用Java的Future接口来实现的，它提供了异步编程的基础能力
     * 3、CompletableFuture实现异步：Java 8中引入的一个异步编程工具类，它提供了更加方便的异步编程方式。
     * 4、Spring的@Async异步：
     *      该注解可以指定自定义线程池，不然默认为线程池为SimpleAsyncTaskExecutor
     * 5、消息队列：一种基于异步消息传递的异步编程方式，它将消息放入队列中，异步处理这些消息。
     */

    public static void main(String[] args) {
        String str = "abcdefg";
        String temp = str.substring(0,2);
        System.out.println("--------temp:"+temp);

        HashMap<Character,Integer> map = new HashMap<>();
        map.put('a',3);
        map.put('b',2);
        map.put('c',1);
        map.put('d',6);
        map.put('e',5);
        map.put('f',4);
        List<Map.Entry<Character,Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if(o1.getValue()==o2.getValue()){
                    return o1.getValue()-o2.getValue();
                }
                return o2.getValue()-o1.getValue();
            }
        });

    }

}
