package com.distribuidora.inventario.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange ordenExchange() {
        return new DirectExchange("orden.exchange");
    }

    @Bean
    public Queue ordenCreadaQueue() {
        return new Queue("orden.creada.queue", true);
    }

    @Bean
    public Binding bindingOrdenCreada(Queue ordenCreadaQueue, DirectExchange ordenExchange) {
        return BindingBuilder.bind(ordenCreadaQueue).to(ordenExchange).with("orden.creada");
    }
}
