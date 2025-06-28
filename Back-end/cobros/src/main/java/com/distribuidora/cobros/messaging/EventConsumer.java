package com.distribuidora.cobros.messaging;

import com.distribuidora.cobros.service.CobroService;
import com.distribuidora.cobros.entity.Transaccion;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    private CobroService servicio;

    @Autowired
    private EventPublisher publisher;

    @RabbitListener(queues = "stock.descontado.queue")
    public void recibirEvento(String mensaje) {
        System.out.println("📥 Cobros recibió: " + mensaje);

        // Extraer ordenId simulado
        String ordenId = "ORD-" + System.currentTimeMillis();

        Transaccion resultado = servicio.procesarPago(ordenId);

        if ("EXITO".equals(resultado.getEstado())) {
            publisher.enviarEvento("pago.exitoso", "Pago exitoso para orden " + ordenId);
        } else {
            publisher.enviarEvento("pago.fallido", "Pago fallido para orden " + ordenId);
        }
    }
}
