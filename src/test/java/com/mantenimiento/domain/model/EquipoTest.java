package com.mantenimiento.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipoTest {

    @Test
    void unEquipoEnServicioMuestraEnServicio() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", true);
        assertEquals("En servicio", equipo.getEstado());
    }

    @Test
    void unEquipoFueraDeServicioMuestraFueraDeServicio() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", false);
        assertEquals("Fuera de servicio", equipo.getEstado());
    }

    @Test
    void elEquipoGuardaSuNombre() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", true);
        assertEquals("Impresora", equipo.getNombre());
    }

    @Test
    void elEquipoGuardaSuUbicacion() {
        Equipo equipo = new Equipo(1L, "Impresora", "Sala 2", true);
        assertEquals("Sala 2", equipo.getUbicacion());
    }
}
