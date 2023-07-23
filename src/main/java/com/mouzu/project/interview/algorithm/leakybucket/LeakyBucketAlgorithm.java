package com.mouzu.project.interview.algorithm.leakybucket;

/**
 * 单线程令牌桶
 *
 * 令牌桶算法简介
 * 令牌桶是指一个限流容器，容器有最大容量，每秒或每100ms产生一个令牌（具体取决于机器每秒处理的请求数），当容量中令牌数量达到最大容量时，
 * 令牌数量也不会改变了，只有当有请求过来时，使得令牌数量减少（只有获取到令牌的请求才会执行业务逻辑），
 * 才会不断生成令牌，所以令牌桶算法是一种弹性的限流算法
 *
 * 令牌桶算法限流范围：
 * 假设令牌桶最大容量为n，每秒产生r个令牌
 *
 * 平均速率：则随着时间推延，处理请求的平均速率越来越趋近于每秒处理r个请求，说明令牌桶算法可以控制平均速率
 * 瞬时速率：如果在一瞬间有很多请求进来，此时来不及产生令牌，则在一瞬间最多只有n个请求能获取到令牌执行业务逻辑，所以令牌桶算法也可以控制瞬时速率
 */
public class LeakyBucketAlgorithm {
    private int capacity = 10;
    private long timeStamp = System.currentTimeMillis();

    public boolean getToken() {
        if (capacity > 0) {
            capacity--;
            return true;
        }
        long current = System.currentTimeMillis();
        if (current - timeStamp >= 100) {
            if ((current - timeStamp) / 100 >= 2) {
                // 假设100ms产生一个令牌
                capacity += (int)((current - timeStamp) / 100) - 1;
            }
            timeStamp = current;
            if (capacity > 10) capacity = 10;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucketAlgorithm leakyBucketAlgorithm = new LeakyBucketAlgorithm();
        while (true) {
//            Thread.sleep(10);
            Thread.sleep(100);
            if (leakyBucketAlgorithm.getToken()) {
                System.out.println("获取令牌成功，可以执行业务逻辑了");
            } else {
                System.out.println("获取令牌失败，请稍后重试");
            }
        }
    }
}
