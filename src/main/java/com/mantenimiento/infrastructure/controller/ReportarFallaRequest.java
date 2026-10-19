package com.mantenimiento.infrastructure.controller;

public record ReportarFallaRequest(
        Long equipoId,
        String equipoNombre,
        String ubicacion,
        String descripcion) {
}
