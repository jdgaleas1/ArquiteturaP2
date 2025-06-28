package com.distribuidora.cobros.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange ordenExchange() {
        return new DirectExchange("orden.exchange");
    }

    @Bean
    public Queue stockDescontadoQueue() {
        return new Queue("stock.descontado.queue", true);
    }

    @Bean
    public Binding bindingStockDescontado(Queue stockDescontadoQueue, DirectExchange ordenExchange) {
        return BindingBuilder.bind(stockDescontadoQueue).to(ordenExchange).with("stock.descontado");
    }
}
