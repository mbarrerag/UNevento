package com.unevento.api.domain.modelo;

import com.unevento.api.domain.records.AddComments;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comentario")
@Getter
@AllArgsConstructor
public class Comentario {

    @Column(name = "id_comentario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcomentario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idusuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Eventos idevento;

    @NotNull
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;

    @OneToMany
    @JoinColumn(name = "id_evento")
    private List<Respuesta> respuestas;

    public Comentario(Usuario usuario, Eventos evento, AddComments addComments) {
        this.idusuario = usuario;
        this.idevento = evento;
        this.comentario = addComments.comentario();
        this.fecha = new Date(); // Inicializa la fecha aquí
    }

    public Comentario() {
        this.fecha = new Date(); // Inicializa la fecha aquí
    }
}
