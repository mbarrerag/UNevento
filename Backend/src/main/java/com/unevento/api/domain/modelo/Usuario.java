package com.unevento.api.domain.modelo;


import com.unevento.api.domain.records.NewUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String correo;
    @NotBlank
    private String password;

    private Byte rol = 0;

    @Column(name = "FECHA_REGISTRO")
    private LocalDateTime fecha_registro = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario_creador")
    private List<Eventos> eventos;

    @OneToMany(mappedBy = "usuario")
    private List<Asistente> asistentes;
    public Usuario() {

    }

    public Usuario(NewUser dataUser) {
        this.nombre = dataUser.nombre();
        this.apellido = dataUser.apellido();
        this.correo = dataUser.email();
        this.password = dataUser.contrasena();
    }


    public void setId(Long id) {
        this.id_usuario = id;
    }

    public Long getId() {
        return id_usuario;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return List.of(new GrantedAuthority() {
         @Override
         public String getAuthority() {
             return "ROLE_USER";
         }
     });
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
