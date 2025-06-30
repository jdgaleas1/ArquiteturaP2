package com.distribuidora.ordenes.dto;

import java.util.List;

public class OrdenDTO {
    
    private String estado;
    private List<DetalleOrdenDTO> productos;
    
    // Constructores
    public OrdenDTO() {}
    
    public OrdenDTO(String estado, List<DetalleOrdenDTO> productos) {
        this.estado = estado;
        this.productos = productos;
    }
    
    // Getters y Setters
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<DetalleOrdenDTO> getProductos() {
        return productos;
    }
    
    public void setProductos(List<DetalleOrdenDTO> productos) {
        this.productos = productos;
    }
    
    // Clase interna para el detalle
    public static class DetalleOrdenDTO {
        private String producto;
        private Integer cantidad;
        
        public DetalleOrdenDTO() {}
        
        public DetalleOrdenDTO(String producto, Integer cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }
        
        public String getProducto() {
            return producto;
        }
        
        public void setProducto(String producto) {
            this.producto = producto;
        }
        
        public Integer getCantidad() {
            return cantidad;
        }
        
        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
}