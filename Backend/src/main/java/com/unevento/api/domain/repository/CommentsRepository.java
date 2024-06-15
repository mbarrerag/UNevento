package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comentario, Long> {
    
    Page<Comentario> findByIdeventocomentarios_Idevento(Long eventoId, Pageable pageable);
}
