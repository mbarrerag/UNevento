package com.unevento.api.domain.modelo;

import com.unevento.api.domain.records.AsistToEvents;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "asistente")
@Getter
@AllArgsConstructor
public class Asistente {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_asistente;


    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Eventos evento;
    @ManyToOne
    @JoinColumn(name = "id_usuario")  // Ajustar aquí
    private Usuario usuario;

    private LocalDateTime fecha_registro = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_boleto")  // Assuming there's a foreign key column named 'boleto_id'
    private Boleto boleto;

    public Asistente() {

    }


    public Asistente(AsistToEvents asistToEvents, Eventos eventos, Usuario usuario) {
     this.usuario = usuario;
     this.evento = eventos;
     this.estado = Estado.valueOf(asistToEvents.estado());
     if(asistToEvents.ifBoleto() == 1){
         Boleto boleto = new Boleto(usuario.getNombre() + usuario.getApellido());
     }
    }

    public void setId_asistente(Long idAsistente) {
        this.id_asistente = idAsistente;
    }

    public Long getId_asistente() {
        return id_asistente;
    }
}