package com.distribuidora.ordenes.service;

import com.distribuidora.ordenes.entity.Orden;
import com.distribuidora.ordenes.repository.OrdenRepository;
import com.distribuidora.ordenes.messaging.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository repo;

    @Autowired
    private EventPublisher publisher;

    public Orden crearOrden(Orden orden) {
        orden.setEstado("CREADA");
        Orden ordenGuardada = repo.save(orden);

        // Publicar evento a RabbitMQ
        publisher.enviarEvento("orden.creada", "Orden ID " + ordenGuardada.getId() + " creada");

        return ordenGuardada;
    }
}
