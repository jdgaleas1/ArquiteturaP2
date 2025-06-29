package com.distribuidora.cobros.controller;

import com.distribuidora.cobros.entity.Transaccion;
import com.distribuidora.cobros.service.CobroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cobros")
public class CobroController {

    @Autowired
    private CobroService servicio;

    @GetMapping
    public ResponseEntity<List<Transaccion>> listarTransacciones() {
        return ResponseEntity.ok(servicio.listarTransacciones());
    }

}
