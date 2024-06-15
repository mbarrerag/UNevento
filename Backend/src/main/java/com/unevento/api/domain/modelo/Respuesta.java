package com.unevento.api.domain.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "respuesta")
@Getter
@Setter
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private long idrespuesta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_comentario")
    private Comentario comentariorespuesta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuariorespuesta;

    @NotNull
    private String comentarioTexto;  // Renombrado para evitar confusión con la entidad Comentario

    @NotNull
    @Column(name = "fecha")
    private Date fecha;

    public Respuesta() {
        this.fecha = new Date();  // Inicializa la fecha aquí
    }
}
