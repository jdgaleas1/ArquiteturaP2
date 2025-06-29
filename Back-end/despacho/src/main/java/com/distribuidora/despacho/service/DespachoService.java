package com.distribuidora.despacho.service;

import com.distribuidora.despacho.entity.Despacho;
import com.distribuidora.despacho.repository.DespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DespachoService {

    @Autowired
    private DespachoRepository repo;

    public Despacho registrarDespacho(Despacho despacho) {
        // Estado inicial puede ser "pendiente"
        despacho.setEstado("pendiente");
        return repo.save(despacho);
    }

    public Despacho prepararDespacho(String ordenId) {
        Despacho despacho = new Despacho();
        despacho.setOrdenId(ordenId);
        despacho.setEstado("LISTO");
        return repo.save(despacho);
    }
    public List<Despacho> listarDespachos() {
        return repo.findAll();
    }

}
