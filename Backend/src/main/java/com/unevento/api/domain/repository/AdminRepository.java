package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findById(Long id);
}
