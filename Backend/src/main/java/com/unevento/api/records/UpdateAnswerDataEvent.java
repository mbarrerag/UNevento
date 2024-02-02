package com.unevento.api.records;

import java.util.Date;

public record UpdateAnswerDataEvent(Long id, Long userID, String nombre, String descripcion, String lugar, String categoria, String Facultad, Date fechaEvento, Long capacidad, String hora) {
}
