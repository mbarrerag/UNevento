package com.unevento.api.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evento")

public class Eventos {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_evento;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String lugar;
    @Enumerated(EnumType.STRING)
    private Facultades status = Facultades.Facultad_De_Ingenieria;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @NotBlank
    private Usuario usuario_crador;
    @ManyToMany
    @JoinColumn(name = "id_asistente")
    private Set<Asistente> asistentes = new HashSet<Asistente>();
    private Date fecha_creacion = new Date();
    private Date fecha_evento = new Date();



    public void setId_evento(Long idEvento) {
        this.id_evento = idEvento;
    }

    public Long getId_evento() {
        return id_evento;
    }
}
