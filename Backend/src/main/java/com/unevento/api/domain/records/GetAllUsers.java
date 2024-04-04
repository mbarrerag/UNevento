package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Usuario;

public record GetAllUsers(Long id, String nombre, String apellido, String email, String contrasena, String imageUrl) {

    public GetAllUsers(Usuario user, String imageUrl) {
        this(user.getIdUsuario(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword(), imageUrl);
    }
}
