package com.mouzu.project.interview.mysql;

public class CommonInfo {
    /**
     * -》追求数据准确性，mysql使用什么数据类型
     * DECIMAL 定点数
     *  浮点数 vs 定点数
     *  浮点数相对于定点数的优点是在长度一定的情况下，浮点类型取值范围大，但是不精准，适用于需要取值范围大，又可以容忍微小误差的科学计算场景
     *  （比如计算化学、分子建模、流体动力学等），从MySQL 8.0.17开始，FLOAT(M,D) 和DOUBLE(M,D)用法在官方文档中已经明确不推荐使用
     *  定点数类型取值范围相对小，但是精准，没有误差，适合于对精度要求极高的场景 （比如涉及金额计算的场景）
     *
     * -》sql的联表查询
     * 内连接：两个表关联起来后，只有当两个表中共同有的数据才进行显示，join和inner join没有本质区别
     * 外连接：
     *  左外连接：返回左表中的所有行，如果左表中行在右表中没有匹配行，则在相关联的结果集中右表的所选择字段均为NULL。
     *  右外连接：返回右表中的所有行，如果右表中行在左表中没有匹配行，则在左表中相关字段返回NULL值。
     *
     *  -》UNION 和 UNION ALL的区别
     *  UNION 和 UNION ALL 是 SQL 中用来合并两个或多个 SELECT 语句的结果集的关键字。
     *  UNION: 会去除重复的记录，返回不重复的结果集。
     *  UNION ALL: 保留所有的记录，包括重复的，返回所有的结果集。
     *
     *  -》explain执行计划理解
     *  使用explain关键字可以模拟优化器执行SQL查询语句，从而知道MySQL是如何处理你的SQL语句的，分析你的查询语句或是表结构的性能瓶颈。
     *  其中最重要的字段为：id、select_type、type、key、rows、Extra
     *  1、id:select查询的序列号，包含一组数字，表示查询中执行select子句或操作表的顺序
     *      id相同执行顺序由上而下，id不同id值越大优先级越高，越先被执行
     *  2、select_type：查询的类型，主要是用于区分普通查询、联合查询、子查询等复杂的查询
     *  3、type：
     *  访问类型，sql查询优化中一个很重要的指标，结果值从好到坏依次是：
     *  system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > ALL
     *  一般来说，好的sql查询至少达到range级别，最好能达到ref
     *  4、key：
     *  实际使用的索引，如果为NULL，则没有使用索引。
     *  查询中如果使用了覆盖索引，则该索引仅出现在key列表中
     *  5、rows：根据表统计信息及索引选用情况，大致估算出找到所需的记录所需要读取的行数
     *  6、extra:表示的是一些额外信息
     *      Using index：使用覆盖索引  Using where：使用 where 语句来处理结果，并且查询的列未被索引覆盖
     *      Using index condition：查询的列不完全被索引覆盖，需要回表查询
     */
}
