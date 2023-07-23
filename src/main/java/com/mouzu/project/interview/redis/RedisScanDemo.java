package com.mouzu.project.interview.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;

public class RedisScanDemo {
    /**
     * -》redis中查询指定key的命令
     * 1、keys命令  数据量大的键就会阻塞进程，导致Redis卡顿
     * 2、scan命令
     * SCAN 命令是一个基于游标的迭代器（cursor based iterator）：SCAN命令每次被调用之后，都会向用户返回一个新的游标，
     * 用户在下次迭代时需要使用这个新游标作为SCAN命令的游标参数，以此来延续之前的迭代过程。当SCAN命令的游标参数被设置为0时，
     * 服务器将开始一次新的迭代， 而当服务器向用户返回值为0的游标时，表示迭代已结束
     *
     * 命令类型：
     * SCAN 命令用于迭代当前数据库中的数据库键
     * SSCAN 命令用于迭代集合键（Set）中的元素
     * HSCAN 命令用于迭代哈希键（Hash）中的键值对
     * ZSCAN 命令用于迭代有序集合（Sorted Set）中的元素（包括元素成员和元素分值）
     */

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void batchDelString(String key, String match, Integer count){
        List<String> result = new ArrayList<>();
        Map mapResult = new HashMap<>(0);
        try {

            // SCAN命令
            Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(
                    ScanOptions.scanOptions().match(match).count(count).build());
            while (cursor.hasNext()) {
                result.add(new String(cursor.next()));
            }

            // SSCAN命令
            Cursor<Object> sScanCursor = redisTemplate.opsForSet().scan(key,
                    ScanOptions.scanOptions().match(match).count(count).build()
            );
            while (sScanCursor.hasNext()) {
                result.add((String) sScanCursor.next());
            }

            // HSCAN命令
            Cursor<Map.Entry<Object, Object>> hScanCursor = redisTemplate.opsForHash().scan(key,
                    ScanOptions.scanOptions().match(match).count(count).build()
            );
            while (hScanCursor.hasNext()) {
                Map.Entry<Object, Object> cursorMap = hScanCursor.next();
                mapResult.put(cursorMap.getKey(), cursorMap.getValue());
            }

            // ZSCAN命令
            Set<ZSetOperations.TypedTuple<Object>> zSetResult = new HashSet<>();
            Cursor<ZSetOperations.TypedTuple<Object>> zScanCursor = redisTemplate.opsForZSet().scan(key,
                    ScanOptions.scanOptions().match(match).count(count).build()
            );
            while (zScanCursor.hasNext()) {
                zSetResult.add(zScanCursor.next());
            }
            cursor.close();

            // 此删除操作可能导致阻塞
            result.forEach(keyStr ->{
                redisTemplate.delete(keyStr);
            });
            redisTemplate.delete(result);

            // 非阻塞删除
            redisTemplate.unlink(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
