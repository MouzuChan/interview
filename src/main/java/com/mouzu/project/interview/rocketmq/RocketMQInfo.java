package com.mouzu.project.interview.rocketmq;

public class RocketMQInfo {
    /**
     * rocketMQ的作用：异步、解耦、降流
     *                 可以实现消息延迟、定时发送（注意如果有消息堆积则从消息堆积后面消费）
     *
     * -》延迟消息实现原理
     * 使用：消息生产端message对象的DelayTimeLevel属性设置延迟功能，共有18级，每一级有对应的延迟时间
     * brocker启动时，根据不同等级开启定时器，即分别对每个延迟等级都开启一个定时器，rocketmq是基于java.util.Timer的定时器
     * 将生产者提供的真实的topic和队列进行备份暂存，然后将消息存储到内置的一个延迟消息topic队列中，
     * 定时器检测消息到时间了，将延迟消息topic队列中消息存储到真实topic和队列
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     *
     * 如何保障rocketMQ消息的可靠性
     * 1、生产端：同步发送、异步发送、oneway方式（不管回调结果）
     * 2、MQ端：消息持久化：同步刷盘（对性能有损耗）
     * 3、消费端：消费端成功消费完消息后会发ACK给MQ确认（集群模式），如果超过消费方不返回ack确认，则进入重发机制，
     *     消息重试16次后还不成功则进入死信队列
     *
     * rocketMQ消费方式有哪些，有什么区别
     * 集群消费和广播消费
     * 集群消费会把所订阅主题的消息平均分摊给消费组所有消费实例，广播模式下每个消费者都会全量消费所有消息
     *
     * -》RocketMQ实现原理
     * RocketMQ由NameServer注册中心集群、Producer生产者集群、Consumer消费者集群和若干Broker（RocketMQ进程）组成
     * 1、Broker在启动的时候去向所有的NameServer注册，并保持长连接，每30s发送一次心跳
     * 2、Producer在发送消息的时候从NameServer获取Broker服务器地址，根据负载均衡算法选择一台服务器来发送消息
     * 3、Conusmer消费消息的时候同样从NameServer获取Broker地址，然后主动拉取消息来消费
     *
     * -》重试消息延时机制
     * 我们说重试消息发到Broker后，被作为一个新的延迟消息存到了CommitLog中，当该消息到了消费时间点是会被Consumer重新消费的。16次后被放入
     * 死信队列，消息的延迟级别是受重试次数（reconsumeTimes）影响的。重试次数越大，延迟越久。
     *
     * -》MQ如何保障分布式事务一致性
     * 1、生产者消息100%投递
     * 2、消费者需要保证幂等性（消费端去保证重复的消息不能进行消费）
     *
     * -》消费端如何保障消息的幂等性
     * 以业务唯⼀标识作为幂等处理的关键依据如：订单号、流⽔号等作为幂等处理的关键依据
     *1、业务操作之前进⾏状态查询
     *2、唯⼀性约束保证最后⼀道防线
     *      上述状态查询操作并不能保证⼀定不出现重复的数据，如：并发插⼊的场景下，如果没有乐观锁、分布式锁作为保证的前提下，
     *      很有可能出现数据的重复插⼊操作，因此我们务必要对幂等id添加唯⼀性索引，这样就能够保证在并发场景下也能保证数据的唯⼀性
     *3、引⼊锁机制
     *      如果是并发更新的情况，没有使⽤悲观锁、乐观锁、分布式锁等机制的前提下，进⾏更新，很可能会出现多次更新导致状态的不准确。
     *      如：对订单状态的更新，业务要求订单只能从初始化->处理中，处理中->成功，处理中->失败，不允许跨状态更新。如果没有锁机制，
     *      很可能会将初始化的订单更新为成功，成功订单更新为失败等异常的情况。⾼并发下，建议通过状态机的⽅式定义好业务状态的变迁，
     *      通过乐观锁、分布式锁机制保证多次更新的结果是确定的，悲观锁在并发环境不利于业务吞吐量的提⾼因此不建议使⽤
     *
     * -》消费消息的两种方式和区别
     * 1、PUSH\PULL
     * 2、push方式里，consumer把轮询过程封装了，并注册MessageListener监听器，取到消息后，唤醒MessageListener的consumeMessage()来消费，
     *      对用户而言，感觉消息是被推送过来的。
     *   pull方式里，取消息的过程需要用户自己写，首先通过打算消费的Topic拿到MessageQueue的集合，遍历MessageQueue集合，
     *      然后针对每个MessageQueue批量取消息，一次取完后，记录该队列下一次要取的开始offset，直到取完了，再换另一个MessageQueue。
     *    区别：push的方式是：消息发送到broker后，如果是push，则broker会主动把消息推送给consumer即topic中，
     *          而pull的方式是:消息投递到broker后，消费端需要主动去broker上拉消息，即需要手动写代码实现，
     *
     */
}
