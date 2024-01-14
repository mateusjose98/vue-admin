package com.dev.gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


@SpringBootApplication
public class GestaoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(GestaoApplication.class, args);
    }


}
