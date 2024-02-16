package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {


    UserDetails findByCorreo(String correo);

    Usuario findByIdUsuario(Long id);

}
