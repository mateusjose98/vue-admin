package com.dev.gestao.messaging.producers;

import com.dev.gestao.messaging.Mensagem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queueName;
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;
    public void send(Mensagem message) {
        log.info("Sending message...");
       try {
           rabbitTemplate.convertAndSend(exchange, queueName, message);
       } catch (Exception e) {
           AmqpRejectAndDontRequeueException amqpReject = new AmqpRejectAndDontRequeueException(e);
           throw amqpReject;
       }
    }
}
