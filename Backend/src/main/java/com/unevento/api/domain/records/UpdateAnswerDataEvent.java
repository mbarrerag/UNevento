package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Categorias;
import com.unevento.api.domain.modelo.Facultades;

import java.util.Date;

public record UpdateAnswerDataEvent(Long id, String nombre, String descripcion, String lugar, Categorias categoria,
                                    Facultades Facultad, Date fechaEvento, Long capacidad, String hora, String
                                            imageUrl, com.unevento.api.domain.modelo.Tipo tipo) {
}
