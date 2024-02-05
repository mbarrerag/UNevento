package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Categorias;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Facultades;

import java.util.Date;

public record GetAllEvenets(String nombre, String descripcion, String lugar, Categorias categoria, Facultades Facultad, Date fechaEvento, Long capacidad, String hora, int acivo) {


    public GetAllEvenets(Eventos eventos) {
        this(eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora(), eventos.getActivo());
    }
}
