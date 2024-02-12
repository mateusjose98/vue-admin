package com.dev.gestao.messaging.producers;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ProducerConfig {


    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queueName;
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;
    @Value("${spring.rabbitmq.request.deadletter.producer}")
    private String deadLetterQueue;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue deadLetterQueue() {
        return new Queue(deadLetterQueue);
    }

    @Bean
    Queue queue() {
        Map<String, Object> args = Map.of("x-dead-letter-exchange", exchange, "x-dead-letter-routing-key", deadLetterQueue);
        return new Queue(queueName, true, false, false, args);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(queueName);
    }

    @Bean
    public Binding bindingDLQ() {
        return BindingBuilder.bind(deadLetterQueue()).to(directExchange()).with(deadLetterQueue);
    }



}
