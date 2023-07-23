package com.mouzu.project.interview.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.ArrayList;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.cluster.nodes}")
    private String CLUSTER_NODES;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        // json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // string序列化配置
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key采用String序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value的序列化方式采用jackson
        template.setValueSerializer(stringRedisSerializer);
        // hash的value采用jackson序列化方式
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StatefulRedisClusterConnection statefulRedisClusterConnection() {
        String[] clusterNodes = CLUSTER_NODES.split(",");
        ArrayList<RedisURI> redisURISList = new ArrayList<>();
        for (String node : clusterNodes) {
            String[] ipAndPort = node.split(":");
            redisURISList.add(new RedisURI(ipAndPort[0], Integer.parseInt(ipAndPort[1]),
                    Duration.ofDays(10)));// Duration.ofDays(10)随便设置，关系不大
        }
        RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURISList);
        StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
        // 实现主从读写分析
        connection.setReadFrom(ReadFrom.REPLICA);
        return connection;
    }


    /**
     * 主从复制的作用
     * 1.数据冗余：实现数据热备份，是持久化之外的一种数据冗余方式
     * 2.故障恢复：当主节点出现问题时，可以由从节点提供服务，实现快速的故障恢复，实际上是一种服务冗余
     * 3.负载均衡: 在主从复制的基础上，配合读写分离，主节点提供写服务，从节点提供读服务，分担服务器负载；
     *            尤其在读多写少的场景下，通过多个从节点分担读负载，可以大大提高redis服务器的并发量
     * 4.高可用基石：主从复制是哨兵和集群能够实施基础，故说是redis高可用的基石
     *
     *
     * 哨兵模式优缺点
     * 优点：
     *      1.哨兵集群，基于主从复制模式，覆盖所有主从配置的优点
     *      2.主从可以切换，故障可以转移，系统的可用性会更好
     *      3.哨兵模式就是主从模式的升级，手动到自动，更加健壮
     *
     * 缺点：
     *      1.redis不好扩容，集群容量一旦到达上限，在线扩容就很难
     *      2.实现哨兵模式的配置有点复杂
     *
     *
     * -》集群模式：
     * 实现了数据的分布式存储，对数据进行分片，将不同的数据存储在不同的master节点上面，从而解决了海量数据的存储问题。
     * 分片的实现原理：16384个哈希槽分布在不同的Redis节点上面进行管理，也就是说每个Redis节点只负责一部分的哈希槽，在对数据进行操作的时候，
     * 集群会对使用CRC16算法对key进行计算并对16384取模，得到结果找到对应的redis节点，然后进行指令操作
     * 分片算法优点：解耦数据和节点之间的关系，简化了扩容和收缩难度
     *
     *默认情况下，redis集群的读和写都是到master上去执行的，不支持slave节点读和写，跟Redis主从复制下读写分离不一样，
     * 因为redis集群的核心的理念，主要是使用slave做数据的热备，以及master故障时的主备切换，实现高可用的。Redis的读写分离，
     * 是为了横向任意扩展slave节点去支撑更大的读吞吐量。而redis集群架构下，本身master就是可以任意扩展的，如果想要支撑更大的读或写的吞吐量，
     * 都可以直接对master进行横向扩展。
     * 实在想实现读写分离，可以引入Lettuce,在连接connection对象设置readFrom为从节点
     *
     *
     * 缓存穿透：redis和数据库都没有请求的key
     * 解决方法：1、缓存空对象
     *            缺点：缓存大量无效的key,即使设置过期时间，也会存在缓存层和数据层不一致
     *         2、布隆过滤器
     *            缺点：key是hash计算，存在hash碰撞，有准确率问题，1.8%
     *
     * 缓存击穿：热点key过期导致请求落在数据库
     * 解决方法：1、设置热点数据永不过期
     *         2、加互斥锁
     *              使用分布式锁，保证对于每个key同时只有一个线程去查询后端服务
     *
     * 缓存雪崩：某个时间点，缓存集中过期失效
     * 解决方法：1、redis高可用，搭建集群防止redis当机
     *         2、限流降级
     *         3、数据预热
     *              正式部署前，把可能的数据预先访问一遍，可能大量访问的数据就会加载在缓存中，在即将发生大并发访问前手动触发加载缓存
     *              不同的key,设置不同的过期时间，让缓存失效的时间点尽量均匀
     *
     * 如何保证缓存和数据库数据的一致性？
     * Cache Aside Pattern（旁路缓存模式）-- 更新 DB，然后直接删除 cache
     * 如果更新数据库成功，而删除缓存这一步失败的情况的话，简单说两个解决方案：
     * 1、缓存失效时间变短（不推荐，治标不治本） ：我们让缓存数据的过期时间变短，这样的话缓存就会从数据库中加载数据。
     *      另外，这种解决办法对于先操作缓存后操作数据库的场景不适用。
     * 2、增加 cache 更新重试机制（常用）： 如果 cache 服务当前不可用导致缓存删除失败的话，我们就隔一段时间进行重试，
     *      重试次数可以自己定。如果多次重试还是失败的话，我们可以把当前更新失败的 key 存入队列中，
     *      等缓存服务可用之后，再将缓存中对应的 key 删除即可。
     *
     * 先删除缓存再更新数据库
     * 缺点：一个线程删除缓存后cpu时间片刚好用完，另一个线程直接读数据库旧数据加载到缓存
     * 延迟双删模式：先删除缓存，再更新数据库，然后延迟一段时间再删除缓存
     * 先更新数据库再删除缓存
     * 缺点：缓存删除失败导致不一致（增加重试机制）
     * 阿里的canal开源组件：更新数据库时会忘binlog中写入日志，通过订阅监听binlog变化，将变更的key发送到MQ，最后更新到缓存
     * 总结：无论哪种方案，都只能保障一点时间内的最终一致性，只要系统涉及高并发频繁的写操作，就无法保证任意时刻的一致性。
     *
     *
     * 为啥是删除而不是更新缓存：
     *  1、多线程并发，更新缓存会出现缓存脏数据
     *  2、写入缓存值经过复杂的计算，更新缓存更消耗性能
     *  3、写多读少的场景，数据没有读就更新了，浪费性能
     *
     * 如何保证 Redis 的高并发
     *  通过主从+集群架构，实现读写分离，主节点负责写，并将数据同步给其他从节点，从节点负责读，从而实现高并发
     *
     *
     *  过期的数据的删除策略：
     *  惰性删除 ：只会在取出 key 的时候才对数据进行过期检查。这样对 CPU 最友好，但是可能会造成太多过期 key 没有被删除。
     *  定期删除 ： 每隔一段时间抽取一批 key 执行删除过期 key 操作。并且，Redis 底层会通过限制删除操作执行的时长和频率
     *              来减少删除操作对 CPU 时间的影响。
     *
     *  定期删除对内存更加友好，惰性删除对 CPU 更加友好。两者各有千秋，所以 Redis 采用的是 定期删除+惰性/懒汉式删除 。
     *  仅仅通过给 key 设置过期时间还是有问题的。因为还是可能存在定期删除和惰性删除漏掉了很多过期 key 的情况。
     *  这样就导致大量过期 key 堆积在内存里，然后就 Out of memory 了。需要Redis 内存淘汰机制
     *
     *  Redis 内存淘汰机制：
     *  Redis 提供 6 种数据淘汰策略：
     *  1、volatile-lru（least recently used）：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
     *  2、volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
     *  3、volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
     *  4、allkeys-lru（least recently used）：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 key（这个是最常用的）
     *  5、allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
     *  6、no-eviction：禁止驱逐数据，也就是说当内存不足以容纳新写入数据时，新写入操作会报错。这个应该没人使用吧！
     *
     *  4.0 版本后增加以下两种：
     *  7、volatile-lfu（least frequently used）：从已设置过期时间的数据集（server.db[i].expires）中挑选最不经常使用的数据淘汰
     *  8、llkeys-lfu（least frequently used）：当内存不足以容纳新写入数据时，在键空间中，移除最不经常使用的 key
     *
     *  redis为啥这么快
     *  1、基于内存
     *  2、单线程执行redis指令，避免多线程上下文切换和线程安全问题
     *  3、网络层面，redis采用IO多路复用，提升了整个并发的处理连接数，6.0开始在多路复用层面采用多线程，提高IO处理能力
     *  4、高级数据结构
     *
     *  redis持久化
     *  RDB：在指定的时间间隔内将内存中的数据集快照写入磁盘
     *      优点：1、文件紧凑，全量备份，非常适合用于进行备份和灾难恢复
     *           2、支持异步处理，主进程不需要进行任何磁盘IO操作
     *           3、在恢复大数据集时的速度比 AOF 的恢复速度要快
     *      缺点：在快照持久化期间修改的数据不会被保存，可能丢失数据
     *
     *  AOF：会将每一个收到的写命令追加到文件中
     *  触发机制：1、修改后立即同步 2、每秒同步 3、让操作系统决定何时进行同步
     *
     *  string底层数据结构实现-》SDS: 简单动态字符串
     *           数据结构组成   1、buf 字符数组用于保存字符串
     *                        2、free 记录buf未使用字节的数量
     *                        3、len buf已使用字节的数量，即字符串长度
     *           优点：
     *              1、获取SDS长度的复杂度为O(1)，len属性记录了字符串本身的长度
     *              2、杜绝缓冲区溢出，空间不满足自动扩展
     *              3、减少修改字符串时带来的内存重分配次数
     *
     *  zset底层数据结构-》跳表：将有序链表改造为支持近似"折半查找"的算法，实现快速增删查
     *
     *  如何优化大key
     *  大key的定义：string：超过10kb 其他类型：元素个数超过5000
     *  如何查找大key：1、redis-cli bigkeys 2、rdbtools工具
     *  如何解决：直接删除大key会阻塞导致性能降低
     *          1、凌晨访问量低的时候用scan命令迭代大key中元素进行分批删除
     *          2、开启lazy free，unlink指令它能对删除操作进行懒处理，丢给后台线程来异步回收内存
     *
     *   如何优化热点key
     *   热 key 带来的问题：请求到的分片过于集中，超过单台 Server 的性能极限。
     *   1）服务端缓存：即将热点数据缓存至服务端的内存中；
     *   2）备份热点Key：即将热点Key+随机数，随机分配至 Redis 其它节点中。
     *
     * -》主从数据同步机制
     * psync+runId+offset
     * 1、全量复制
     *  从服务器发送 psync ? -1
     *  主服务器接收到命令后答复 FULLSYNC runId offset,然后用bgsave生成一份RDB文件，期间新增的数据操作命令放在缓存
     *  主服务器给从服务器发送RDB文件，完后发送期间新增的数据操作命令
     *  从服务器接收并同步数据
     *  此后 master 每执行一个写命令，就向Slave发送相同的写命令
     *
     * 2、增量复制
     * A、如果出现网络闪断或者命令丢失等异常情况时，当主从连接恢复后，由于从节点之前保存了自身已复制的偏移量和主节点的运行ID。
     * 因此会把它们当作psync参数发送给主节点，要求进行部分复制操作
     * B、主节点接到psync命令后首先核对参数runId是否与自身一致，如果一致，说明之前复制的是当前主节点；之后根据参数offset在自身复
     * 制积压缓冲区查找，如果偏移量之后的数据存在缓冲区中，则对从节点发送+CONTINUE响应，表示可以进行部分复制；否则进行全量复制
     * C、主节点根据偏移量把复制积压缓冲区里的数据发送给从节点，保证主从复制进入正常状态
     *
     *
     * -》pipeline的理解
     * redis的pipeline用在指令批处理上，通过减少TCP 连接中了 “交互往返” 的时间实现性能的提升。并且它的实现原理是队列，
     * 队列保障先进先出，保障消息的有序性。
     * 过多使用缺点：redis server必须在处理完所有命令前先缓存起所有命令的处理结果。打包的命令越多，缓存消耗内存也越多。
     *
     * -》Redis集群方案不可用的情况
     * 1、集群主库半数宕机(根据 failover 原理，fail 掉一个主需要一半以上主都投票通过才可以)
     * 2、集群某一节点的主从全数宕机
     *
     * -》redis集群的主从复制模型
     * 每个节点都会有 N-1 个复制品
     *
     * -》redis集群写操作丢失的情况
     * 1、过期 key 被清理
     * 2、最大内存不足，导致 Redis 自动清理部分 key 以节省空间
     * 3、主库故障后自动重启，从库自动同步
     * 4、单独的主备方案，网络不稳定触发哨兵的自动切换主从节点，切换期间会有数据丢失
     *
     * -》redis事务相关指令
     * MULTI EXEC DISCARD 和 WATCH
     * watch:监视一或多个key,如果在事务执行之前,被监视的key被其他命令改动，则事务被打断 （ 类似乐观锁 ）
     *
     * -》redis实现分布式锁原理
     * 1、set key value nx expireTime获取锁
     * 2、释放锁前先判断，如果是锁的持有者则删除锁（为了判断和删除这两个动作的原子性，可以加入lua脚本去实现）
     * 3、针对线程还未执行完毕锁已过期的情况，可以引入看门狗实现锁续期（启动一个watch dog看门狗，它是一个后台线程，会每隔10秒检查一下，
     *      锁续命周期就是设置的超时时间的三分之一，如果线程还持有锁，就会不断的延长锁key的生存时间）
     *
     * -》redis集群锁丢失问题解决方法
     * Redis作者 antirez提出一种高级的分布式锁算法：Redlock。redlock是一种基于多节点redis实现分布式锁的算法，
     * 可以有效解决redis单点故障的问题。官方建议搭建五台redis服务器对redlock算法进行实现。
     * Redlock原理：搞多个Redis master部署，以保证它们不会同时宕掉。并且这些master节点是完全相互独立的，相互之间不存在数据同步。
     * 同时，需要确保在这多个master实例上，是与在Redis单实例，使用相同方法来获取和释放锁。当超过半数的redis节点加锁成功才算成功获取锁
     */

}
