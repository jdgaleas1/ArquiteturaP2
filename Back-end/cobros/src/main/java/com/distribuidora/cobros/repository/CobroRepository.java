package com.distribuidora.cobros.repository;

import com.distribuidora.cobros.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobroRepository extends JpaRepository<Transaccion, Long> {
}
