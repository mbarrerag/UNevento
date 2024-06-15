package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Usuario;

public record GetUsers(Long id, String nombre, String apellido, String email, String contrasena, String imageUrl) {

    public GetUsers(Usuario user) {
        this(user.getIdUsuario(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword(), user.getImagen_path());
    }
}