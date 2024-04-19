package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Tipo;
import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Eventos, Long> {

    Page<Eventos> findByTipo(Tipo tipo, Pageable pageable);


    Page<Eventos> findByActivoTrue(Pageable pageable);

    //    Eventos findById_eventoOrID(Long id);
    Eventos findByIdevento(Long id);

    Page<Eventos> findByUsuarioCreador(Usuario usuario, Pageable pageable);

}
