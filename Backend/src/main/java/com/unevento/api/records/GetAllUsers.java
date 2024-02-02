package com.unevento.api.records;

import com.unevento.api.modelo.Usuario;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public record GetAllUsers(Long id, String nombre, String apellido, String email, String contrasena) {

    public GetAllUsers(Usuario user) {
        this(user.getId(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword());
    }
}
