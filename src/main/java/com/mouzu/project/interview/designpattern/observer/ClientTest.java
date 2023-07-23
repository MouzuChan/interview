package com.mouzu.project.interview.designpattern.observer;

/**
 * 观察者模式：
 *      多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 *      这种模式有时又称作发布-订阅模式、模型-视图模式，它是对象行为型模式
 * 角色结构:
 *      Subject：抽象主题（抽象被观察者），抽象主题角色把所有观察者对象保存在一个集合里，每个主题都可以有任意数量的观察者，
 *              抽象主题提供一个接口，可以增加和删除观察者对象。
 *      ConcreteSubject：具体主题（具体被观察者），该角色将有关状态存入具体观察者对象，在具体主题的内部状态发生改变时，
 *                      给所有注册过的观察者发送通知。
 *      Observer：抽象观察者，是观察者的抽象类，它定义了一个更新接口，使得在得到主题更改通知时更新自己。
 *      ConcrereObserver：具体观察者，实现抽象观察者定义的更新接口，以便在得到主题更改通知时更新自身的状态。
 */
public class ClientTest {
    public static void main(String[] args) {
        SubscriptionSubject mSubscriptionSubject=new SubscriptionSubject();
        //创建微信用户
        WechatUser user1=new WechatUser("小明");
        WechatUser user2=new WechatUser("小王");
        WechatUser user3=new WechatUser("小李");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("NBA的文章更新啦！！！");
    }
}
