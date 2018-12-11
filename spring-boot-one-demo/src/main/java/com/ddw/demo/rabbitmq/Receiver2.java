package com.ddw.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

//@Component
public class Receiver2 {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = RabbitDemo.queueName_two)
    public void receiveMessage(Integer message) {
        System.out.println("Received2 <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}