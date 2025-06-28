package com.distribuidora.inventario.messaging;

import com.distribuidora.inventario.service.InventarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    private InventarioService servicio;

    @Autowired
    private EventPublisher publisher;

    @RabbitListener(queues = "orden.creada.queue")
    public void recibirEvento(String mensaje) {
        System.out.println("📥 Inventario recibió: " + mensaje);

        // Simulamos extracción del nombre del producto
        String producto = "Laptop";
        boolean descontado = servicio.descontarStock(producto, 1);

        if (descontado) {
            publisher.enviarEvento("stock.descontado", "Stock descontado para " + producto);
        } else {
            publisher.enviarEvento("stock.fallo", "Sin stock para " + producto);
        }
    }
}
