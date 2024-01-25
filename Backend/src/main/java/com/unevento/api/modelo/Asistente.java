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


    @ManyToMany(mappedBy = "asistentes")
    private Set<Eventos> eventos = new HashSet<Eventos>();

    @ManyToOne
    @JoinColumn(name = "id_usuario")  // Ajustar aquí
    private Usuario usuario;

    private Date fecha_registro = new Date();

    @ManyToOne
    @JoinColumn(name = "id_boleto")  // Assuming there's a foreign key column named 'boleto_id'
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
