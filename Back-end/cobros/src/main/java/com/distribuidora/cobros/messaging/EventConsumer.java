package com.distribuidora.cobros.messaging;

import com.distribuidora.cobros.entity.Transaccion;
import com.distribuidora.cobros.service.CobroService;
import com.distribuidora.cobros.messaging.EventPublisher;
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

      //  cobroService.procesarPagoDesdeMensaje(mensaje);
        Transaccion transaccion = servicio.procesarPagoDesdeMensaje(mensaje);

        if (transaccion != null && "EXITO".equals(transaccion.getEstado())) {
            publisher.enviarEvento("pago.exitoso", "{\"ordenId\":\"" + transaccion.getOrdenId() + "\"}");
        } else {
            publisher.enviarEvento("pago.fallido", "{\"ordenId\":\"" + (transaccion != null ? transaccion.getOrdenId() : "desconocida") + "\"}");
        }
    }
}
