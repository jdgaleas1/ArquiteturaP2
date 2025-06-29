package com.distribuidora.inventario.messaging;

import com.distribuidora.inventario.entity.Producto;
import com.distribuidora.inventario.repository.InventarioRepository;
import com.distribuidora.inventario.service.InventarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import com.distribuidora.inventario.dto.OrdenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        try {
            ObjectMapper mapper = new ObjectMapper();
            OrdenDTO orden = mapper.readValue(mensaje, OrdenDTO.class);

            String producto = orden.getProducto();
            int cantidad = orden.getCantidad();
            String ordenId = orden.getOrdenId();

            Optional<Producto> productoOpt = repo.findByNombre(producto);
            if (productoOpt.isPresent()) {
                boolean descontado = servicio.descontarStock(producto, cantidad);
                if (descontado) {
                    // ✅ enviar JSON correcto
                    String json = "{\"ordenId\":\"" + ordenId + "\"}";
                    publisher.enviarEvento("stock.descontado", json);
                } else {
                    String json = "{\"ordenId\":\"" + ordenId + "\", \"error\":\"Sin stock\"}";
                    publisher.enviarEvento("stock.fallo", json);
                }
            } else {
                System.out.println("⚠️ Producto no encontrado en inventario: " + producto);
                String json = "{\"ordenId\":\"" + ordenId + "\", \"error\":\"Producto no encontrado\"}";
                publisher.enviarEvento("stock.fallo", json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
