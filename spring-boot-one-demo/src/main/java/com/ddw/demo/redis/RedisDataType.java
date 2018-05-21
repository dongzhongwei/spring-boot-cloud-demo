package com.ddw.demo.redis;

import com.ddw.demo.redis.queue.RedisQueueDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RedisDataType {

    public static void main(String[] args) throws Exception {

        final ApplicationContext ctx = SpringApplication.run(RedisQueueDemo.class, args);
        final StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);

        //string 类型
        final ValueOperations<String, String> valueOperations = template.opsForValue();
        String stringKey = "string:key";
        //set 设置存储在给定键中的值
        valueOperations.set(stringKey, "hello");
        //append
        valueOperations.append(stringKey, " world");
        //setrange 将从start偏移量开始的字串设置为给定值
        valueOperations.set(stringKey, ",", 5);
        //getrange
        System.out.println("getrange: " + valueOperations.get(stringKey, 5, 10));
        //get 获取存储在给定键中的值
        System.out.println("get: " + valueOperations.get(stringKey));
        //getbit
        //del 删除存储在给定键中的值
        template.delete(stringKey);
        System.out.println("get: " + valueOperations.get("hello"));
        //incr 将键存储的值加上1
        //incrby incrby key-name amount 将键存储的值加上整数amount
        //incrbyfloat
        final Long incr = valueOperations.increment("string:incr", 2);
        System.out.println("incr: " + incr);
        //decr 将键存储的值减去1
        //decrby decrby key-name amount 将键村春的值减去整数amount
        final Long decr = valueOperations.increment("string:decr", -1);
        System.out.println("decr: " + decr);


        //list 类型
        final ListOperations<String, String> listOperations = template.opsForList();
        String listKey = "list:key";
        //rpush
        listOperations.rightPush(listKey, "rightList1");
        listOperations.rightPushAll(listKey, "rightList2", "rightList3");
        listOperations.rightPushAll(listKey, Arrays.asList("rightList4", "rightList5"));
        //lpush
        listOperations.leftPush(listKey, "leftList1");
        listOperations.leftPushAll(listKey, "leftList2", "leftList3");
        listOperations.leftPushAll(listKey, Arrays.asList("leftList4", "leftLeft5"));
        //lange
        System.out.println("lange: " + listOperations.range(listKey, 0, -1));
        //rpop
        System.out.println("rpop: " + listOperations.rightPop(listKey));
        //brpop 从第一个非空列表中填出位于最右端的元素，或者在timeout秒之内阻塞并等待客淡出的元素出现
        System.out.println("brpop: " + listOperations.rightPop(listKey, 5, TimeUnit.SECONDS));
        //lpop
        System.out.println("lpop: " + listOperations.leftPop(listKey));
        //lindex
        System.out.println(listOperations.index(listKey, 7));
        //ltrim 对列表进行裁剪,只保留从start偏移量到end偏移量范围内的元素
        listOperations.trim(listKey, 0, -1);
        //rpoplpush
        System.out.println(listOperations.rightPopAndLeftPush(listKey, listKey));
        System.out.println("lange: " + listOperations.range(listKey, 0, -1));
        template.delete(listKey);


        //set 类型
        final SetOperations<String, String> setOperations = template.opsForSet();
        String setKey = "set:key";
        //sadd
        System.out.println(setOperations.add(setKey, "set1", "set2", "set3"));
        System.out.println(setOperations.add(setKey, "set3", "set4", "set5"));
        //srem
        System.out.println(setOperations.remove(setKey, "set1", "set2"));
        //scard
        System.out.println("set size: " + setOperations.size(setKey));
        //sismember
        System.out.println(setOperations.isMember(setKey, "set5"));
        //smembers
        System.out.println("set: " + setOperations.members(setKey));
        //srandmember
        System.out.println("srandmember: " + setOperations.randomMember(setKey));
        //smove
        System.out.println("smove: " + setOperations.move(setKey, "set5", setKey));

        String setKey1 = "set:key1";
        setOperations.add(setKey1, "set1", "set3", "set5");
        //sdiff  返回那些存在与第一个集合、但不存在于其他集合中的元素  差集
        System.out.println("sdiff: " + setOperations.difference(setKey, setKey1));
        //sdiffstore 将那些存在于第一个集合但不存在于其他集合中的元素存储到 dest-key 键中
        setOperations.differenceAndStore(setKey, setKey1, "set:key:1");
        System.out.println(setOperations.difference(setKey, "set:key:1"));
        //sinter 交集运算
        System.out.println(setOperations.intersect(setKey, setKey1));
        //sinterstore
        setOperations.intersectAndStore(setKey, setKey1, "set:key:2");
        System.out.println(setOperations.members("set:key:2"));
        //sunion 并集
        System.out.println(setOperations.union(setKey, setKey1));


        //hash 类型
        final HashOperations<String, String, String> hashOperations = template.opsForHash();
        String hashKey = "hash:key";
        //hmset
        hashOperations.put(hashKey, "k1", "v1");
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("k2", "v2");
        hashMap.put("k3", "3");
        hashOperations.putAll(hashKey, hashMap);
        //hgetall
        System.out.println(hashOperations.entries(hashKey));
        ///hmget
        System.out.println(hashOperations.get(hashKey, "k3"));
        //hdel
        hashOperations.delete(hashKey, "k1");
        //hlen
        System.out.println(hashOperations.size(hashKey));
        //hkeys
        System.out.println(hashOperations.keys(hashKey));
        //hvals
        System.out.println(hashOperations.values(hashKey));
        //hexists
        System.out.println(hashOperations.hasKey(hashKey, "k2"));
        //hincrby
        System.out.println(hashOperations.increment(hashKey, "k3", 1));

        //zset 类型
        final ZSetOperations<String, String> zSetOperations = template.opsForZSet();
        String zSetKey = "zSet:key";
        //zadd
        zSetOperations.add(zSetKey, "a", 3);
        zSetOperations.add(zSetKey, "b", 2);
        zSetOperations.add(zSetKey, "c", 1);
        //zcard
        zSetOperations.size(zSetKey);
        //zcount
        System.out.println("zcount: " + zSetOperations.count(zSetKey, 1.5, 2.5));
        //zrank
        System.out.println("zrank: " + zSetOperations.rank(zSetKey, "c"));

        System.exit(0);
    }


} 