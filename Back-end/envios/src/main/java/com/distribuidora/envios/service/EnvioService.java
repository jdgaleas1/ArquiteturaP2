package com.distribuidora.envios.service;

import com.distribuidora.envios.entity.Envio;
import com.distribuidora.envios.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repo;

    public Envio procesarEnvio(String ordenId) {
        Envio envio = new Envio();
        envio.setOrdenId(ordenId);
        envio.setEstado("ENVIADO");
        return repo.save(envio);
    }
}
