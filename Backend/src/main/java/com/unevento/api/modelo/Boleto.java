package com.unevento.api.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boleto")
@AllArgsConstructor

public class Boleto {
    @Id
    private Long id_boleto;

    @OneToMany(mappedBy = "evento")
    private Set<Eventos> eventos = new HashSet<Eventos>();

    @OneToMany(mappedBy = "boleto")
    private Set<Asistente> asistentes = new HashSet<Asistente>();

    private int cantidad;

    public Boleto() {

    }

    public void setId_boleto(Long idBoleto) {
        this.id_boleto = idBoleto;
    }

    public Long getId_boleto() {
        return id_boleto;
    }
}
