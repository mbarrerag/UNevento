package com.unevento.api.domain.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boleto")
@AllArgsConstructor
@Getter
@Setter

public class Boleto {

    
    @Id
    @Column(name = "ID_BOLETO_BOLETOS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idboleto;

    @OneToOne
    @JoinColumn(name = "ID_BOLETO_BOLETOS", referencedColumnName = "idBoleto")
    private Boleto boleto;

    private String nombre_usuario;

    public Boleto() {

    }


    public Boleto(String nombre_usuarios) {
        this.nombre_usuario = nombre_usuarios;
    }

    public Long getId_boleto() {
        return idboleto;
    }

    public void setId_boleto(Long idBoleto) {
        this.idboleto = idBoleto;
    }
}
