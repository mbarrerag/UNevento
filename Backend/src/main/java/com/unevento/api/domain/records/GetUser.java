package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Usuario;

public record GetUser(Long idUsuario, String nombre, String apellido, String email, String path) {
    public GetUser(Usuario usuario) {
        this(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getImagen_path()
        );
    }


}
