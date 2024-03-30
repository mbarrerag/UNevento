package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Categorias;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Facultades;

import java.util.Date;

public record GetAllUserEvents(Long id, String nombre, String descripcion, String lugar, Categorias categoria,
                               Facultades facultad, Date fechaEvento, Long capacidad, String hora, int activo,
                               String imagenUrl) {

    public GetAllUserEvents(Eventos eventos, String imageUrl) {
        this(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(),
                eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(),
                eventos.getHora(), eventos.getActivo(), imageUrl);
    }
}
