package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
