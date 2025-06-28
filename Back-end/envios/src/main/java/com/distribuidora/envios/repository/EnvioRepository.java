package com.distribuidora.envios.repository;

import com.distribuidora.envios.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
}
