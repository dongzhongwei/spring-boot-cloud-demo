package com.ddw.demo.mq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    static final String TOPIC_EXCHANGE_NAME = "zjob-topic-exchange";

    /**
     * 创建交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }



}
