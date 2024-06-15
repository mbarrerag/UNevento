package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comentario, Long> {

    @Query("SELECT c FROM Comentario c WHERE c.ideventocomentarios.idevento = :eventoId")
    Page<Comentario> findByIdeventocomentarios_Idevento(@Param("eventoId") Long eventoId, Pageable pageable);

}
