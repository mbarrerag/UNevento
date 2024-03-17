package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Eventos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Eventos, Long> {

    Page<Eventos> findByActivoTrue(Pageable pageable);

    //    Eventos findById_eventoOrID(Long id);
    Eventos findByIdevento(Long id);

}
