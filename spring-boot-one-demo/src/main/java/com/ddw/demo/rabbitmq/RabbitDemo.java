package com.ddw.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

//@SpringBootApplication
//@EnableRabbit
public class RabbitDemo {

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName_one = "spring-boot-queue-1";

    static final String queueName_two = "spring-boot-queue-2";


    /**
     * 创建队列
     * @return
     */
    @Bean
    Queue queueOne() {
        return new Queue(queueName_one, false);
    }

    @Bean
    Queue queueTwo() {
        return new Queue(queueName_two, false);
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    /**
     * 队列和交换机绑定
     * routingKey 通配符的路由配置
     * 1、"#"表示匹配一个或多个词
     * 2、"*"表示只匹配一个词
     * @param queueOne
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queueOne, TopicExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange).with("foo.bar.#");
    }

    @Bean
    Binding bindingTwo(Queue queueTwo, TopicExchange exchange) {
        return BindingBuilder.bind(queueTwo).to(exchange).with("foo.baz.#");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory transResumeRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new
                SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
//        factory.setConcurrentConsumers(3);
//        factory.setMaxConcurrentConsumers(10);
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        return factory;
    }


//    /**
//     * 消息监听容器配置
//     * @param connectionFactory
//     * @param listenerAdapter
//     * @return
//     */
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName_one,queueName_two);
//        container.setMessageListener(listenerAdapter);
//        //应答机制
//        container.setAcknowledgeMode(AcknowledgeMode.NONE);
//        return container;
//    }


//    /**
//     * 监听消费
//     * @param receiver
//     * @return
//     */
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RabbitDemo.class, args).close();
    }



}
