package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Usuario;

public record GetAllUsers(Long id, String nombre, String apellido, String email, String contrasena) {

    public GetAllUsers(Usuario user) {
        this(user.getIdUsuario(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword());
    }
}
