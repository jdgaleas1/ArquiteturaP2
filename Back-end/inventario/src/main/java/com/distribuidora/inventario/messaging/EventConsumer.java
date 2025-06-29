package com.distribuidora.inventario.messaging;

import com.distribuidora.inventario.entity.Producto;
import com.distribuidora.inventario.repository.InventarioRepository;
import com.distribuidora.inventario.service.InventarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventConsumer {

    @Autowired
    private InventarioService servicio;

    @Autowired
    private InventarioRepository repo;

    @Autowired
    private EventPublisher publisher;

    @RabbitListener(queues = "orden.creada.queue")
    public void recibirEvento(String mensaje) {
        System.out.println("📥 Inventario recibió: " + mensaje);

        // Simulamos extracción del nombre del producto desde el mensaje
        String producto = "Laptop"; // Puedes cambiar esto por lógica real de parsing

        // Validar si el producto existe antes de intentar descontar
        Optional<Producto> productoOpt = repo.findByNombre(producto);
        if (productoOpt.isPresent()) {
            boolean descontado = servicio.descontarStock(producto, 1);

            if (descontado) {
                publisher.enviarEvento("stock.descontado", "Stock descontado para " + producto);
            } else {
                publisher.enviarEvento("stock.fallo", "Sin stock para " + producto);
            }
        } else {
            System.out.println("⚠️ Producto no encontrado en inventario: " + producto);
            publisher.enviarEvento("stock.fallo", "Producto no encontrado: " + producto);
        }
    }
}
