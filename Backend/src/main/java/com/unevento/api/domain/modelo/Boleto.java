package com.unevento.api.domain.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boleto")
@AllArgsConstructor

public class Boleto {
    @Id
    private Long id_boleto;

    @OneToMany(mappedBy = "boleto")
    private Set<Asistente> asistentes = new HashSet<Asistente>();

    private String nombre_usuario;

    public Boleto() {

    }

    public Boleto(String nombre_usuarios) {
        this.nombre_usuario = nombre_usuarios;
    }

    public void setId_boleto(Long idBoleto) {
        this.id_boleto = idBoleto;
    }

    public Long getId_boleto() {
        return id_boleto;
    }
}
