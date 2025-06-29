package com.distribuidora.ordenes.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEvento(String routingKey, String ordenId, String producto) {
        try {
            Map<String, Object> mensaje = new HashMap<>();
            mensaje.put("ordenId", ordenId);
            mensaje.put("producto", producto);

            String json = mapper.writeValueAsString(mensaje);

            rabbitTemplate.convertAndSend("orden.exchange", routingKey, json);
            System.out.println("📤 Evento enviado: " + routingKey + " -> " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
