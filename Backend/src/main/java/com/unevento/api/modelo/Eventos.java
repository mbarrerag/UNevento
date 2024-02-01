package com.unevento.api.modelo;

import com.unevento.api.controllers.NewEvents;
import com.unevento.api.records.NewEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "evento")
@Getter
@Setter
@AllArgsConstructor

public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String lugar;

    @NotBlank
    private String hora;
    @NotNull
    @Column(name = "CANTIDAD_MAX_ASISTENTES")
    private Long capacidad;
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    @Enumerated(EnumType.STRING)
    private Facultades facultad;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario_creador;
    @ManyToMany
    @JoinTable(
            name = "evento",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_asistente")

    )
    private List<Asistente> asistentes;
    @Column(name = "FECHA_REGISTRO")
    private Date fecha_creacion = new Date();
    private Date fecha_evento = new Date();
    private int activo = 1;

    public  Eventos(NewEvent dataEvent, Usuario user){
        this.usuario_creador = user;
        this.nombre = dataEvent.nombre();
        this.descripcion = dataEvent.descripcion();
        this.lugar = dataEvent.lugar();
        this.categoria = Categorias.valueOf(dataEvent.categoria());
        this.facultad = Facultades.valueOf(dataEvent.Facultad());
        this.fecha_evento = dataEvent.fechaEvento();
        this.capacidad = dataEvent.capacidad();
        this.hora = dataEvent.hora();
    }

    public Eventos(NewEvents newEvents) {

    }

    public Eventos() {

    }


    public void setId_evento(Long idEvento) {
        this.id_evento = idEvento;
    }

    public Long getId_evento() {
        return id_evento;
    }
}
