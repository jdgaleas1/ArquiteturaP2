package com.distribuidora.ordenes.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEvento(String routingKey, String mensaje) {
        rabbitTemplate.convertAndSend("orden.exchange", routingKey, mensaje);
        System.out.println("📤 Evento enviado: " + routingKey + " -> " + mensaje);
    }
}
