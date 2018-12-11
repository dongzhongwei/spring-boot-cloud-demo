package com.ddw.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

//@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

//    @RabbitListener(queues = RabbitDemo.queueName_one, containerFactory = "transResumeRabbitListenerContainerFactory")
    public void receiveMessage(Integer message) {
        if (message instanceof Integer){
            Integer msg =  Integer.class.cast(message);
            if (msg % 2 == 0){
//                System.err.println("出错了：" + msg);
                throw new RuntimeException("抛出异常");
            }
        }
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}