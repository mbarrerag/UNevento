package com.unevento.api.records;

import com.unevento.api.modelo.Eventos;

import java.util.Date;

public record GetAllEvenets(String nombre, String descripcion, String lugar, com.unevento.api.modelo.Categorias categoria, com.unevento.api.modelo.Facultades Facultad, Date fechaEvento, Long capacidad, String hora, int acivo) {


    public GetAllEvenets(Eventos eventos) {
        this(eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora(), eventos.getActivo());
    }
}
