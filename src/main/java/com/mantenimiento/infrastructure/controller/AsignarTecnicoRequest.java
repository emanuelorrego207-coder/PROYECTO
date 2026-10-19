package com.mantenimiento.infrastructure.controller;

public record AsignarTecnicoRequest(
        Long tecnicoId,
        String nombre,
        String especialidad) {
}
