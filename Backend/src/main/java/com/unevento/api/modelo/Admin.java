package com.unevento.api.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "admin")
@Getter
@AllArgsConstructor

public class Admin {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_admin;
    @NotBlank
    private String correo;
    @NotBlank
    private String password;
    @NotBlank
    private Byte rol;

    public Admin() {

    }

    public void setId(Long id) {
        this.id_admin = id;
    }

    public Long getId() {
        return id_admin;
    }
}
