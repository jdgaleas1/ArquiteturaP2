package com.distribuidora.despacho.messaging;

import com.distribuidora.despacho.entity.Despacho;
import com.distribuidora.despacho.repository.DespachoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class EventConsumer {

    @Autowired
    private DespachoRepository repo;

    @Autowired
    private EventPublisher publisher;

    @RabbitListener(queues = "stock.descontado.queue")
    public void recibirEvento(String mensaje) {
        System.out.println("📥 Despacho recibió: " + mensaje);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(mensaje);

            String ordenId = json.has("ordenId") ? json.get("ordenId").asText() : "ORD-" + System.currentTimeMillis();

            // Crear entidad despacho
            Despacho despacho = new Despacho();
            despacho.setOrdenId(ordenId);
            despacho.setEstado("DESPACHADO");

            repo.save(despacho);

            // Publicar evento de despacho exitoso
            publisher.enviarEvento("orden.despachada", "Orden " + ordenId + " despachada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
