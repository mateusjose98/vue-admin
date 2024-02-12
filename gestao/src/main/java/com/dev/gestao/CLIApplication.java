package com.dev.gestao;

import com.dev.gestao.messaging.Mensagem;
import com.dev.gestao.messaging.producers.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CLIApplication implements CommandLineRunner {

    @Autowired
    private Producer producer;
    @Override
    public void run(String... args) throws Exception {
        producer.send(new Mensagem("ERRO2"));
    }
}
