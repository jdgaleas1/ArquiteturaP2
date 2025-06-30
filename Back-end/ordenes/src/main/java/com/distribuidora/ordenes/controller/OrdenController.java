package com.distribuidora.ordenes.controller;

import com.distribuidora.ordenes.dto.OrdenDTO;
import com.distribuidora.ordenes.entity.Orden;
import com.distribuidora.ordenes.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ordenes")
@CrossOrigin(origins = "*")
public class OrdenController {

    @Autowired
    private OrdenService servicio;

    @PostMapping
    public ResponseEntity<Orden> crear(@RequestBody OrdenDTO ordenDTO) {
        return ResponseEntity.ok(servicio.crearOrden(ordenDTO));
    }
    
    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        return ResponseEntity.ok(servicio.listarOrdenes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerOrden(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Long id, @RequestBody OrdenDTO ordenDTO) {
        return ResponseEntity.ok(servicio.actualizarOrden(id, ordenDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}