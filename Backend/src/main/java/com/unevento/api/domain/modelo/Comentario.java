package com.unevento.api.domain.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Eventos ideventocomentarios;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;


    // Constructor vacío
    public Comentario() {
    }

    // Constructor con parámetros
    public Comentario(Usuario usuario, Eventos evento, String comentario) {
        this.idUsuario = usuario;
        this.ideventocomentarios = evento;
        this.comentario = comentario;
        this.fecha = new Date();
    }
}
