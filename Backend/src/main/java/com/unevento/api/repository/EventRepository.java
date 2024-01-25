package com.unevento.api.repository;

import com.unevento.api.modelo.Eventos;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Eventos, Long> {

}
