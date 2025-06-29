package com.distribuidora.cobros.service;

import com.distribuidora.cobros.entity.Transaccion;
import com.distribuidora.cobros.repository.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.ArrayList; // ✅ Asegúrate de importar esto

@Service
public class CobroService {

    @Autowired
    private CobroRepository cobroRepository;


    public Transaccion procesarPagoDesdeMensaje(String mensaje) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(mensaje);

            String ordenId = json.get("ordenId").asText();  // asegúrate de que el mensaje JSON tenga "ordenId"
            return procesarPago(ordenId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Transaccion procesarPago(String ordenId) {
        Transaccion trans = new Transaccion();
        trans.setOrdenId(ordenId);
        trans.setEstado("EXITO"); // Si quieres que todos los pagos sean exitosos
        return cobroRepository.save(trans);

    }

    public List<Transaccion> listarTransacciones() {
        return cobroRepository.findAll();
    }
    public List<Transaccion> obtenerTodosLosCobros() {
        List<Transaccion> lista = cobroRepository.findAll();
        return lista != null ? lista : new ArrayList<>();
    }
}
