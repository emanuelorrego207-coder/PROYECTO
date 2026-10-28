package com.mantenimiento.domain.model;

import com.mantenimiento.domain.exception.ReglaNegocioException;

public class Tecnico extends Usuario {

    private final String especialidad;

    public Tecnico(Long id, String nombre, String especialidad) {
        super(id, nombre);
        if (nombre == null || nombre.isBlank()) {
            throw new ReglaNegocioException("El nombre del técnico es obligatorio");
        }
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
