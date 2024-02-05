package com.unevento.api.domain.records;

import java.util.Date;

public record UpdateEvent(Long id, String nombre, String descripcion, String lugar, String categoria, String Facultad, Date fechaEvento, Long capacidad, String hora) {
}
