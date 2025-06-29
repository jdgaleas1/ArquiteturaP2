package com.distribuidora.despacho.controller;

import com.distribuidora.despacho.entity.Despacho;
import com.distribuidora.despacho.service.DespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/despacho")
public class DespachoController {

    @Autowired
    private DespachoService servicio;

    @GetMapping
    public ResponseEntity<List<Despacho>> listarDespachos() {
        System.out.println("✅ Entró al endpoint /despacho");
        return ResponseEntity.ok(servicio.listarDespachos());
    }
    @PostMapping
    public ResponseEntity<Despacho> registrar(@RequestBody Despacho despacho) {
        return ResponseEntity.ok(servicio.registrarDespacho(despacho));
    }

}
