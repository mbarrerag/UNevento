package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Asistente;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistentRepository extends JpaRepository<Asistente, Long> {

    Asistente findByUsuarioAndEvento(Usuario usuario, Eventos evento);
}
