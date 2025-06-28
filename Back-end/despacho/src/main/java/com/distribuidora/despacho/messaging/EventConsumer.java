package com.distribuidora.despacho.messaging;

import com.distribuidora.despacho.service.DespachoService;
import com.distribuidora.despacho.entity.Despacho;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    private DespachoService servicio;

    @Autowired
    private EventPublisher publisher;

    @RabbitListener(queues = "pago.exitoso.queue")
    public void recibirEvento(String mensaje) {
        System.out.println("📥 Despacho recibió: " + mensaje);

        // Simulamos extracción de ordenId desde el mensaje
        String ordenId = "ORD-" + System.currentTimeMillis();

        Despacho despacho = servicio.prepararDespacho(ordenId);

        publisher.enviarEvento("despacho.preparado", "Despacho preparado para orden " + despacho.getOrdenId());
    }
}
