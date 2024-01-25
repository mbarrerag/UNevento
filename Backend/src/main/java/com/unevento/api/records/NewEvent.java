package com.unevento.api.records;

import java.util.Date;

public record NewEvent(Long userID, String nombre, String descripcion, String lugar, String categoria, String Facultad, Date fechaEvento, int capacidad, String hora) {
}
