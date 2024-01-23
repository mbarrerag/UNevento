package com.unevento.api.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_usuario;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String correo;
    @NotBlank
    private String password;
    @NotBlank
    private Byte rol;
    @NotBlank
    private Date fecha_registro;

    @OneToMany(mappedBy = "usuario_creador")
    private List<Eventos> eventos;

    @OneToMany(mappedBy = "usuario")
    private List<Asistente> asistentes;
    public Usuario() {

    }


    public void setId(Long id) {
        this.id_usuario = id;
    }

    public Long getId() {
        return id_usuario;
    }
}
