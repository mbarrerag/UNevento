package com.unevento.api.domain.modelo;

import com.unevento.api.controllers.NewEvents;
import com.unevento.api.domain.records.NewEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evento")
@Getter
@Setter
@AllArgsConstructor

public class Eventos {
    @Column(name = "ID_EVENTO")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idevento;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String lugar;

    @NotBlank
    private String hora;


    private String imagen_path;

    @NotNull
    @Column(name = "CANTIDAD_MAX_ASISTENTES")
    private Long capacidad;
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    @Enumerated(EnumType.STRING)
    private Facultades facultad;
    @NotNull
    @Column(name = "TIPO_EVENTO")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioCreador;
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

    public Eventos(NewEvent dataEvent, Usuario user, String imagen_path) {
        this.usuarioCreador = user;
        this.nombre = dataEvent.nombre();
        this.descripcion = dataEvent.descripcion();
        this.lugar = dataEvent.lugar();
        this.categoria = Categorias.valueOf(dataEvent.categoria());
        this.facultad = Facultades.valueOf(dataEvent.Facultad());
        this.fecha_evento = dataEvent.fechaEvento();
        this.capacidad = dataEvent.capacidad();
        this.hora = dataEvent.hora();
        this.imagen_path = imagen_path;
        this.tipo = Tipo.valueOf(dataEvent.tipo());

    }

    public Eventos(NewEvents newEvents) {

    }

    public Eventos() {

    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idEvento) {
        this.idevento = idEvento;
    }
}
