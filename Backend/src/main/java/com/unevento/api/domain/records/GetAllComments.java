package com.unevento.api.domain.records;

import java.util.Date;
import java.util.List;

public record GetAllComments(Long idComentario, String comentario, Date fecha, GetUser usuario,
                             List<GetAllAnswers> respuestas) {


}
