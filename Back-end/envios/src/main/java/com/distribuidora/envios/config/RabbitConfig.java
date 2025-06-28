package com.distribuidora.envios.config;

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
    public Queue despachoPreparadoQueue() {
        return new Queue("despacho.preparado.queue", true);
    }

    @Bean
    public Binding bindingDespachoPreparado(Queue despachoPreparadoQueue, DirectExchange ordenExchange) {
        return BindingBuilder.bind(despachoPreparadoQueue).to(ordenExchange).with("despacho.preparado");
    }
}
