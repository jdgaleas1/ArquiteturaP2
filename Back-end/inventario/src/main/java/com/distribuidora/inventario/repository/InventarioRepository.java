package com.distribuidora.inventario.repository;

import com.distribuidora.inventario.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}
