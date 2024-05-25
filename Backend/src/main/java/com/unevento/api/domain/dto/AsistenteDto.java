package com.unevento.api.domain.dto;

public class AsistenteDto {
    private String nombre;
    private String apellido;
    private String correo;
    private String imagen_path;

    public AsistenteDto(String nombre, String apellido, String correo, String imagen_path) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.imagen_path= imagen_path;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getImagen_path() {
        return imagen_path;
    }
}