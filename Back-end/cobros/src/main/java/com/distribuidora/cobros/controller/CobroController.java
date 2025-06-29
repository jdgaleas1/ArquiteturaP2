package com.distribuidora.cobros.controller;

import com.distribuidora.cobros.entity.Transaccion;
import com.distribuidora.cobros.service.CobroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cobros") // Esto asegura que la ruta sea /cobros
public class CobroController {

    @Autowired
    private CobroService cobroService;

    @GetMapping
    public ResponseEntity<List<Transaccion>> listarTransacciones() {
        System.out.println(">>> Entró al endpoint /cobros");
        List<Transaccion> lista = cobroService.listarTransacciones(); // Aquí uso correctamente la variable
        System.out.println(">>> Se recuperaron: " + lista.size() + " cobros.");
        return ResponseEntity.ok(lista);
    }
}
