package com.example.product;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "product-queue";
    public static final String EXCHANGE_NAME = "order-exchange";
    public static final String ROUTING_KEY = "order.created";

    // 1. Define the Queue
    @Bean
    public Queue productQueue() {
        return new Queue(QUEUE_NAME, true); // durable = true
    }

    // 2. Define the Exchange
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 3. Bind the Queue to the Exchange using the Routing Key
    @Bean
    public Binding binding(Queue productQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(productQueue)
                .to(orderExchange) 
                .with(ROUTING_KEY);
    }
}