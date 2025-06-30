package com.distribuidora.ordenes.service;

import com.distribuidora.ordenes.dto.OrdenDTO;
import com.distribuidora.ordenes.entity.DetalleOrden;
import com.distribuidora.ordenes.entity.Orden;
import com.distribuidora.ordenes.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Transactional
    public Orden crearOrden(OrdenDTO ordenDTO) {
        // Crear la orden
        Orden orden = new Orden(ordenDTO.getEstado());
        
        // Agregar los detalles
        for (OrdenDTO.DetalleOrdenDTO detalleDTO : ordenDTO.getProductos()) {
            DetalleOrden detalle = new DetalleOrden(
                detalleDTO.getProducto(), 
                detalleDTO.getCantidad()
            );
            orden.agregarDetalle(detalle);
        }
        
        return ordenRepository.save(orden);
    }

    public List<Orden> listarOrdenes() {
        return ordenRepository.findAll();
    }

    public Orden obtenerOrden(Long id) {
        return ordenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    @Transactional
    public Orden actualizarOrden(Long id, OrdenDTO ordenDTO) {
        Orden orden = obtenerOrden(id);
        
        // Actualizar estado
        orden.setEstado(ordenDTO.getEstado());
        
        // Limpiar detalles existentes
        orden.getDetalles().clear();
        
        // Agregar nuevos detalles
        for (OrdenDTO.DetalleOrdenDTO detalleDTO : ordenDTO.getProductos()) {
            DetalleOrden detalle = new DetalleOrden(
                detalleDTO.getProducto(), 
                detalleDTO.getCantidad()
            );
            orden.agregarDetalle(detalle);
        }
        
        return ordenRepository.save(orden);
    }

    @Transactional
    public void eliminarOrden(Long id) {
        if (!ordenRepository.existsById(id)) {
            throw new RuntimeException("Orden no encontrada");
        }
        ordenRepository.deleteById(id);
    }
}