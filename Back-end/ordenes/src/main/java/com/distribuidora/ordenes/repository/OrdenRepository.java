package com.distribuidora.ordenes.repository;

import com.distribuidora.ordenes.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {}
