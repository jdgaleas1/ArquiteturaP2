package com.distribuidora.cobros.service;

import com.distribuidora.cobros.entity.Transaccion;
import com.distribuidora.cobros.repository.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.List;

@Service
public class CobroService {

    @Autowired
    private CobroRepository repo;

    public Transaccion procesarPago(String ordenId) {
        Transaccion trans = new Transaccion();
        trans.setOrdenId(ordenId);

        // Simular resultado de pago (éxito o fallo)
        //boolean exito = new Random().nextBoolean();
     //   trans.setEstado(exito ? "EXITO" : "FALLO");

        // Forzar siempre éxito
        trans.setEstado("EXITO");


        return repo.save(trans);
    }
    public List<Transaccion> listarTransacciones() {
        return repo.findAll();
    }

}
