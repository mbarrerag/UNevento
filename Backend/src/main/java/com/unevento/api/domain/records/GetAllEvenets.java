package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Categorias;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Facultades;
import com.unevento.api.domain.modelo.Usuario;

import java.util.Date;

public record GetAllEvenets(Long id, String nombre, String descripcion, String lugar, Categorias categoria,
                            Facultades facultad, Date fechaEvento, Long capacidad, String hora, int activo, Long idUsuario,
                            String imagenUrl) {

    public GetAllEvenets(Eventos eventos, String imageUrl) {
        this(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(),
                eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(),
                eventos.getHora(), eventos.getActivo(),eventos.getUsuarioCreador().getIdUsuario(), imageUrl);
    }
}
