package com.distribuidora.ordenes.controller;

import com.distribuidora.ordenes.entity.Orden;
import com.distribuidora.ordenes.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService servicio;

    @PostMapping
    public ResponseEntity<Orden> crear(@RequestBody Orden orden) {
        return ResponseEntity.ok(servicio.crearOrden(orden));
    }

    @GetMapping("/test")
    public String test() {
        return "Microservicio Órdenes funcionando ✔️";
    }
}
