package com.ddw.demo.redis.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

//@SpringBootApplication
public class RedisQueueDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisQueueDemo.class);

    public static void main(String[] args) throws InterruptedException {
        final ApplicationContext ctx = SpringApplication.run(RedisQueueDemo.class, args);
        final StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        final CountDownLatch latch = ctx.getBean(CountDownLatch.class);
        LOGGER.info("Sending message...");
        template.convertAndSend("chat","Hello from Redis!");
        latch.await();
        System.exit(0);
    }

}
