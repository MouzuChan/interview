package com.mouzu.project.interview.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流控器
 * 如果限流开始，则只能有max个请求因此等待，超过此值则直接拒绝
 *
 * RateLimiter简介
 * Google开源工具包Guava提供了限流工具类RateLimiter,该类基于令牌桶算法(Token Bucket)来完成限流,非常易于使用.
 * RateLimiter经常用于限制对一些物理资源或者逻辑资源的访问速率.它支持两种获取permits接口,一种是如果拿不到立刻返回false,
 * 一种会阻塞等待一段时间看能不能拿到.
 */
public class FollowController {
    private final RateLimiter rateLimiter;

    private int maxPermits;

    private Object mutex = new Object();

    //等待获取permits的请求个数，原则上可以通过maxPermits推算
    private int maxWaitingRequests;

    private AtomicInteger waitingRequests = new AtomicInteger(0);

    public FollowController(int maxPermits, int maxWaitingRequests) {
        this.maxPermits = maxPermits;
        this.maxWaitingRequests = maxWaitingRequests;
        rateLimiter = RateLimiter.create(maxPermits);
    }

    public FollowController(int maxPermits, long warmUpPeriodAsSecond, int maxWaitingRequests) {
        this.maxPermits = maxPermits;
        this.maxWaitingRequests = maxWaitingRequests;
        rateLimiter = RateLimiter.create(maxPermits, warmUpPeriodAsSecond, TimeUnit.SECONDS);
    }

    public boolean acquire() {
        return acquire(1);
    }

    public boolean acquire(int permits) {
        boolean success = rateLimiter.tryAcquire(permits);
        if (success) {
            rateLimiter.acquire(permits);//可能有出入
            return true;
        }
        if (waitingRequests.get() > maxWaitingRequests) {
            return false;
        }
        waitingRequests.getAndAdd(permits);
        rateLimiter.acquire(permits);

        waitingRequests.getAndAdd(0 - permits);
        return true;
    }
}
