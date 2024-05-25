package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Asistente;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.unevento.api.domain.dto.AsistenteDto;
import java.util.List;

public interface AsistentRepository extends JpaRepository<Asistente, Long> {

    Asistente findByUsuarioAndEvento(Usuario usuario, Eventos evento);

    @Query("SELECT new com.unevento.api.domain.dto.AsistenteDto(u.nombre, u.apellido, u.correo, u.imagen_path) " +
            "FROM Asistente a INNER JOIN a.usuario u " +
            "WHERE a.evento.idevento = :eventoId AND a.estado = 'CONFIRMADO'")
    List<AsistenteDto> findConfirmedAsistentesByEventoId(@Param("eventoId") Long eventoId);
}
