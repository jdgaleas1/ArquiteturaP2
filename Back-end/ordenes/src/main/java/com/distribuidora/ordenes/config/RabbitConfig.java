package com.distribuidora.ordenes.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange ordenExchange() {
        return new DirectExchange("orden.exchange");
    }
}
