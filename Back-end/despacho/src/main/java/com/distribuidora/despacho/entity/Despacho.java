package com.distribuidora.despacho.entity;

import jakarta.persistence.*;

@Entity
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ordenId;
    private String estado; // "LISTO" o "PENDIENTE"

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrdenId() { return ordenId; }
    public void setOrdenId(String ordenId) { this.ordenId = ordenId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
