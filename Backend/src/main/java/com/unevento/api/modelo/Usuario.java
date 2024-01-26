package com.unevento.api.modelo;


import com.unevento.api.records.NewUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String correo;
    @NotBlank
    private String password;

    private Byte rol = 0;

    @Column(name = "FECHA_REGISTRO")
    private LocalDateTime fecha_registro = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario_creador")
    private List<Eventos> eventos;

    @OneToMany(mappedBy = "usuario")
    private List<Asistente> asistentes;
    public Usuario() {

    }

    public Usuario(NewUser dataUser) {
        this.nombre = dataUser.nombre();
        this.apellido = dataUser.apellido();
        this.correo = dataUser.email();
        this.password = dataUser.contrasena();
    }


    public void setId(Long id) {
        this.id_usuario = id;
    }

    public Long getId() {
        return id_usuario;
    }


}
