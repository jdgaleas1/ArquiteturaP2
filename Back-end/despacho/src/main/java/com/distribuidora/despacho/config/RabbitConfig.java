package com.distribuidora.despacho.config;

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
    public Queue pagoExitosoQueue() {
        return new Queue("pago.exitoso.queue", true);
    }

    @Bean
    public Binding bindingPagoExitoso(Queue pagoExitosoQueue, DirectExchange ordenExchange) {
        return BindingBuilder.bind(pagoExitosoQueue).to(ordenExchange).with("pago.exitoso");
    }
}
