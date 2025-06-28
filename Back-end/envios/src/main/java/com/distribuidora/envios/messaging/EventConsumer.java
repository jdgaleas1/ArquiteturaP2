package com.distribuidora.envios.messaging;

import com.distribuidora.envios.service.EnvioService;
import com.distribuidora.envios.entity.Envio;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    private EnvioService servicio;

    @RabbitListener(queues = "despacho.preparado.queue")
    public void recibirEvento(String mensaje) {
        System.out.println("📥 Envios recibió: " + mensaje);

        // Simulación de extracción de ordenId
        String ordenId = "ORD-" + System.currentTimeMillis();

        Envio envio = servicio.procesarEnvio(ordenId);
        System.out.println("🚚 Envío registrado para orden " + envio.getOrdenId());
    }
}
