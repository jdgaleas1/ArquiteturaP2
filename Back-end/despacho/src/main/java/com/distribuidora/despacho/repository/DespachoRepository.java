package com.distribuidora.despacho.repository;

import com.distribuidora.despacho.entity.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespachoRepository extends JpaRepository<Despacho, Long> {
}
