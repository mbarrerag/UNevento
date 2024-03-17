package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}
