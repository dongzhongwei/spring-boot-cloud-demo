package com.ddw.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

//@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");

        //消息生产者1
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitDemo.topicExchangeName, "foo.bar.baz", i);
        }

//        for (int i = 100; i < 110; i++) {
//            rabbitTemplate.convertAndSend(RabbitDemo.topicExchangeName, "foo.bar.baz.two", i);
//        }


        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitDemo.topicExchangeName, "foo.baz.bar", i);
        }


//        rabbitTemplate.convertAndSend(RabbitDemo.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(30000, TimeUnit.MILLISECONDS);
    }
}