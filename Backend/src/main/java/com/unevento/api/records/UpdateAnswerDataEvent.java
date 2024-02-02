package com.unevento.api.records;

import java.util.Date;

public record UpdateAnswerDataEvent(Long id, String nombre, String descripcion, String lugar, com.unevento.api.modelo.Categorias categoria, com.unevento.api.modelo.Facultades Facultad, Date fechaEvento, Long capacidad, String hora) {
}
