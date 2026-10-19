package com.mantenimiento.domain.model;

public abstract class Usuario {

    private Long id;
    private String nombre;

    public Usuario(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String mostrarInformacion();
}
