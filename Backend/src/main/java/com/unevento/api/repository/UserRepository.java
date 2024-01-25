package com.unevento.api.repository;

import com.unevento.api.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
