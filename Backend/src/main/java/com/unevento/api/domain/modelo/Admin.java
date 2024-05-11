package com.unevento.api.domain.modelo;

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
    private Long id;
    @NotBlank
    private String correo;
    @NotBlank
    private String password;
    @NotBlank
    private Byte rol;

    public Admin() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
