package com.unevento.api.domain.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boleto")
@AllArgsConstructor
@Getter
@Setter

public class Boleto {


    @Id
    @Column(name = "ID_BOLETO_BOLETOS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_boleto;

    @OneToMany(mappedBy = "boleto")
    private Set<Asistente> asistentes = new HashSet<Asistente>();

    private String nombre_usuario;

    public Boleto() {

    }


    public Boleto(String nombre_usuarios) {
        this.nombre_usuario = nombre_usuarios;
    }

    public Long getId_boleto() {
        return id_boleto;
    }

    public void setId_boleto(Long idBoleto) {
        this.id_boleto = idBoleto;
    }
}
