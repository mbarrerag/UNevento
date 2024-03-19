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
    @JoinColumn(name = "evento_id")
    private Eventos evento;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_EVENTO")  // Ajustar aquí
    private Usuario usuario;

    private LocalDateTime fecha_registro = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "ID_BOLETO") // nombre de la columna de la clave foránea en la tabla Asistente
    private Boleto boleto;

    public Asistente() {

    }


    public Asistente(AsistToEvents asistToEvents, Eventos eventos, Usuario usuario, Boleto boleto) {
        this.usuario = usuario;
        this.evento = eventos;
        this.estado = Estado.valueOf(asistToEvents.estado());
        this.boleto = boleto;
        //     if(asistToEvents.ifBoleto() == 1){
//         Boleto boleto = new Boleto(usuario.getNombre() + usuario.getApellido());
//     }
    }

    public Long getId_asistente() {
        return id_asistente;
    }

    public void setId_asistente(Long idAsistente) {
        this.id_asistente = idAsistente;
    }
}