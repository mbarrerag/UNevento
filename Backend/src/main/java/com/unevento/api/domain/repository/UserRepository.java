package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {


    UserDetails findByNombre(String username);
}
