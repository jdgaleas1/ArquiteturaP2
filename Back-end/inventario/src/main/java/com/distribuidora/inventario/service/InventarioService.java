package com.distribuidora.inventario.service;

import com.distribuidora.inventario.entity.Producto;
import com.distribuidora.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repo;

    public boolean descontarStock(String nombre, int cantidad) {
        Optional<Producto> productoOpt = repo.findByNombre(nombre);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                repo.save(producto);
                return true;
            }
        }
        return false;
    }

    public Producto registrarProducto(Producto producto) {
        return repo.save(producto);
    }

    public List<Producto> listarProductos() {
        return repo.findAll();
    }

}
