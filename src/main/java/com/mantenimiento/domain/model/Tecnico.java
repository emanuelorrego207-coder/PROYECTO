package com.mantenimiento.domain.model;

public class Tecnico extends Usuario {

    private String especialidad;

    public Tecnico(Long id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String mostrarInformacion() {
        return "Técnico: " + getNombre() + " - Especialidad: " + especialidad;
    }
}
