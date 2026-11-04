package com.mantenimiento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de SolicitudMantenimiento.
 * Integrante: Emanuel Orrego
 */
class SolicitudEstadoTest {

    Equipo equipo = new Equipo(1L, "Computador Dell", "Laboratorio", true);
    SolicitudMantenimiento solicitud = new SolicitudMantenimiento(1L, equipo, "No enciende");

    // Prueba de Emanuel Orrego: una solicitud recién creada queda en estado PENDIENTE
    @Test
    void unaSolicitudNuevaEstaPendiente() {
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
    }

    // Prueba de Emanuel Orrego: una solicitud recién creada no tiene técnico asignado
    @Test
    void unaSolicitudNuevaNoTieneTecnico() {
        assertFalse(solicitud.tieneTecnico());
    }

    // Prueba de Emanuel Orrego: una solicitud recién creada no está finalizada
    @Test
    void unaSolicitudNuevaNoEstaFinalizada() {
        assertFalse(solicitud.estaFinalizada());
    }

    // Prueba de Emanuel Orrego: la solicitud guarda la descripción de la falla
    @Test
    void laSolicitudGuardaLaDescripcion() {
        assertEquals("No enciende", solicitud.getDescripcion());
    }
}
