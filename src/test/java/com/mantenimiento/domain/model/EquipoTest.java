package com.mantenimiento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de Equipo.
 * Integrante: Juan Andres Rios
 */
class EquipoTest {

    // Prueba de Juan Andres Rios: un equipo en servicio muestra "En servicio"
    @Test
    void unEquipoEnServicioMuestraEnServicio() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", true);
        assertEquals("En servicio", equipo.getEstado());
    }

    // Prueba de Juan Andres Rios: un equipo fuera de servicio muestra "Fuera de servicio"
    @Test
    void unEquipoFueraDeServicioMuestraFueraDeServicio() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", false);
        assertEquals("Fuera de servicio", equipo.getEstado());
    }

    // Prueba de Juan Andres Rios: el equipo guarda su nombre
    @Test
    void elEquipoGuardaSuNombre() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", true);
        assertEquals("Impresora", equipo.getNombre());
    }

    // Prueba de Juan Andres Rios: el equipo guarda su ubicación
    @Test
    void elEquipoGuardaSuUbicacion() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", true);
        assertEquals("Sala 2", equipo.getUbicacion());
    }
}
