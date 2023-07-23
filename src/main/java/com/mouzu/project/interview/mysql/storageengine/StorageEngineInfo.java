package com.mouzu.project.interview.mysql.storageengine;

public class StorageEngineInfo {
    /**
     * 存储引擎：innodb、myisam、memory、DBD
     *
     * innodb、myisam适用场景
     * innodb是一种事务型存储引擎，对可靠性有要求的业务一般都用innodb;myisam是一种olap的存储引擎，适合读多写少的场景，像公司年度总结的功能
     * 比较适合使用myisam
     *
     * 在只读场景中mysiam比innodb执行快的原因：
     * mysiam底层存储结构是非聚簇索引，所有索引存储的数据是具体记录行的地址，不需像innodb那样回表；而且innodb查询需要维护MVCC，导致比较慢
     *
     * * redolog、undolog、binlog
     *   redolog实现持久性、undolog实现原子性、binlog实现一致性
     *
     * redolog
     * 是InnoDB存储引擎独有的，它让MySQL拥有了崩溃恢复能力。比如 MySQL 实例挂了或宕机了，重启时，InnoDB存储引擎会使用redo log恢复数据，
     * 保证数据的持久性与完整性。
     *
     *
     * 结合redolog和undolog 说一说一条sql 的执行过程
     * 1、从磁盘中加载数据到buffer pool中
     * 2、记录Undolog日志
     * 3、更新buffer pool中的数据
     * 4、记录redolog日志就是将更新的操作写入redolog buffer中
     * 5、redolog buffer 写入 到redolog文件
     * 6、记录binlog日志（刷盘）
     *
     * redolog刷盘策略
     * InnoDB 存储引擎为 redo log 的刷盘策略提供了 innodb_flush_log_at_trx_commit 参数，它支持三种策略：
     * 0：设置为 0 的时候，表示每次事务提交时不进行刷盘操作
     * 1：设置为 1 的时候，表示每次事务提交时都将进行刷盘操作（默认值）
     * 2：设置为 2 的时候，表示每次事务提交时都只把 redo log buffer 内容写入 page cache
     *
     * 只要每次把修改后的数据页直接刷盘不就好了，还有 redo log 什么事（为啥需要redolog）？
     * 数据页刷盘是随机写，因为一个数据页对应的位置可能在硬盘文件的随机位置，所以性能是很差;redolog是顺序写，刷盘速度很快，可以提高数据库的并发
     * 能力
     *
     * -》脏页何时刷盘以及怎样刷盘
     * 从磁盘中加载数据页到buffer pool后，一旦对数据页进行DML操作，数据页会变成脏页，被放进Flash链表中，
     * 1、内存不足需要淘汰数据页
     * 2、磁盘上的redo log日志文件写满时：redo log大小固定，写完后会循环覆盖写入，写满后当有新内容要写入时，
     *      系统必须停止所有的更新操作，将checkpoint向前推进到新的位置，但是在推进之前必须将覆盖部分的所有脏页都flush到磁盘上
     * 3、系统空闲的时候后台会定期flush适量的脏页到磁盘
     * 4、MySQL正常关闭时，会把所有脏页都flush到磁盘
     *
     * 为什么需要二阶段提交：
     * 两阶段提交是分布式事务的常见解决方案，在mysql里sqlserver和存储引擎也算是分布式事务；通过二阶段提交保障数据的一致性。redolog在prepare
     * 阶段进行刷盘，事务提交后，待binlog写入成功后再回调redolog提交。这样可以保障两点，如果binlog写入失败则回滚事务，如果redolog commit
     * 失败，则查询binlog是否有对应的事务ID，如果有则继续进行提交。
     *
     * uodolog的作用：
     * 1、事务回滚
     * 2、MVCC实现依赖
     */
}
