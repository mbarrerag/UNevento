package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Facultades;
import com.unevento.api.domain.modelo.Tipo;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.GetAllEvenets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface EventRepository extends JpaRepository<Eventos, Long> {

    Page<Eventos> findByTipo(Tipo tipo, Pageable pageable);


    Page<Eventos> findByActivoTrue(Pageable pageable);

    //    Eventos findById_eventoOrID(Long id);
    Eventos findByIdevento(Long id);

    Page<Eventos> findByUsuarioCreador(Usuario usuario, Pageable pageable);

    Page<Eventos> findByFacultadAndTipo(Facultades facultades, Tipo tipo, Pageable pageable);

    @Query("SELECT e FROM Eventos e WHERE e.tipo = :tipo AND e.fecha_evento >= :currentDate ORDER BY e.fecha_evento ASC")
    Page<Eventos> findByTipoAndFechaAfterOrEqual(Tipo tipo, Timestamp currentDate, Pageable pageable);

    //Consulta de Eventos a los cuales ha asistido un usuario con determinado id
    @Query("SELECT new com.unevento.api.domain.records.GetAllEvenets(e, e.imagen_path, cast((select count(a) from Asistente a where a.evento = e) as long)) " +
            "FROM Eventos e INNER JOIN e.asistentes a " +
            "WHERE a.usuario.idUsuario = :usuarioId AND e.fecha_evento >= :currentDate " +
            "ORDER BY e.fecha_evento ASC")
    Page<GetAllEvenets> findEventsByUsuarioIdAndFechaAfterOrEqual(@Param("usuarioId") Long usuarioId, @Param("currentDate") Timestamp currentDate, Pageable pageable);}
