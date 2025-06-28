package com.distribuidora.inventario.controller;

import com.distribuidora.inventario.entity.Producto;
import com.distribuidora.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService servicio;

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(servicio.registrarProducto(producto));
    }

    @GetMapping("/test")
    public String test() {
        return "✅ Microservicio Inventario en línea";
    }
}
