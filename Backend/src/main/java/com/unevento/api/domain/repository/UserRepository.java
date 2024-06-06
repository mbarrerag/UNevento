package com.unevento.api.domain.repository;

import com.unevento.api.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario, Long> {


    UserDetails findByCorreo(String correo);

    Usuario findByIdUsuario(Long id);

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:nameOrLastName% OR u.apellido LIKE %:nameOrLastName%")
    List<Usuario> findByNombreOrApellido(@Param("nameOrLastName") String nameOrLastName);

}
