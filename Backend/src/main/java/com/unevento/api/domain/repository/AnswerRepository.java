package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Respuesta, Long> {

    Page<Respuesta> findByComentariorespuesta(Comentario comentario, Pageable pageable);
}