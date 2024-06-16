package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Eventos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentsRepository extends JpaRepository<Comentario, Long> {

    Page<Comentario> findByIdevento(Eventos eventos, Pageable pageable);

    Comentario findByIdcomentario(Long id);
}