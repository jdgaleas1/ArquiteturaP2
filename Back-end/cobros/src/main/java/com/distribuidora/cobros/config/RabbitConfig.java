package com.distribuidora.cobros.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Declaración del exchange central usado por todos los microservicios
    @Bean
    public DirectExchange ordenExchange() {
        return new DirectExchange("orden.exchange");
    }

    // Cola que escucha el microservicio de Cobros para stock descontado
    @Bean
    public Queue stockDescontadoQueue() {
        return new Queue("stock.descontado.queue", true); // durable
    }

    // Enlace de la cola con el exchange usando la routing key correspondiente
    @Bean
    public Binding bindingStockDescontado(Queue stockDescontadoQueue, DirectExchange ordenExchange) {
        return BindingBuilder
                .bind(stockDescontadoQueue)
                .to(ordenExchange)
                .with("stock.descontado");
    }
    @Bean
    public Queue pagoExitosoQueue() {
        return new Queue("pago.exitoso.queue", true);
    }

    @Bean
    public Binding bindingPagoExitoso(Queue pagoExitosoQueue, DirectExchange ordenExchange) {
        return BindingBuilder.bind(pagoExitosoQueue).to(ordenExchange).with("pago.exitoso");
    }
    @Bean
    public Queue ordenCreadaQueue() {
        return new Queue("orden.creada.queue", true);
    }

    @Bean
    public Binding bindingOrdenCreada(Queue ordenCreadaQueue, DirectExchange ordenExchange) {
        return BindingBuilder
                .bind(ordenCreadaQueue)
                .to(ordenExchange)
                .with("orden.creada");
    }

}
