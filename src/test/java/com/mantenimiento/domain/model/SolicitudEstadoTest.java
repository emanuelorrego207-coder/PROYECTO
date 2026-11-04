package com.mantenimiento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Autor: Emanuel Orrego
class SolicitudEstadoTest {

    Equipo equipo = new Equipo(1L, "Computador Dell", "Laboratorio", true);
    SolicitudMantenimiento solicitud = new SolicitudMantenimiento(1L, equipo, "No enciende");

    @Test
    void unaSolicitudNuevaEstaPendiente() {
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
    }

    @Test
    void unaSolicitudNuevaNoTieneTecnico() {
        assertFalse(solicitud.tieneTecnico());
    }

    @Test
    void unaSolicitudNuevaNoEstaFinalizada() {
        assertFalse(solicitud.estaFinalizada());
    }

    @Test
    void laSolicitudGuardaLaDescripcion() {
        assertEquals("No enciende", solicitud.getDescripcion());
    }
}
