package com.distribuidora.despacho.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
        return new Queue("stock.descontado.queue", true); // esta es la que escucha EventConsumer
    }

    @Bean
    public Binding bindingStockDescontado(Queue stockDescontadoQueue, DirectExchange ordenExchange) {
        return BindingBuilder
                .bind(stockDescontadoQueue)
                .to(ordenExchange)
                .with("stock.descontado");
    }
}
