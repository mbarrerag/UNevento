package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comentario, Long> {
}
