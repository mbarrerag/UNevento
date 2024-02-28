package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Estado;

public record AsistToEvents(Long idusuario, String estado, Long eventoid, Long ifBoleto) {
}
