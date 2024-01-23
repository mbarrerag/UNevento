package com.unevento.api.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asistente")
@Getter
@AllArgsConstructor
public class Asistente {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_asistente;


    @OneToMany(mappedBy = "asistente")
    private Set<Eventos> eventos = new HashSet<Eventos>();

    @OneToOne(mappedBy = "usuario")
    private Usuario usuario;

    private Date fecha_registro = new Date();

    @OneToOne(mappedBy = "boleto")
    private Boleto boleto;

    public Asistente() {

    }

    public void setId_asistente(Long idAsistente) {
        this.id_asistente = idAsistente;
    }

    public Long getId_asistente() {
        return id_asistente;
    }
}
