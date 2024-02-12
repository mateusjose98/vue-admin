package com.dev.gestao.messaging.consumers;

import com.dev.gestao.messaging.Mensagem;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consume(Mensagem message) {

            System.out.println("Message received: " + message);


    }

}
