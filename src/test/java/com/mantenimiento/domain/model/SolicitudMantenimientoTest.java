package com.mantenimiento.domain.model;

import com.mantenimiento.domain.exception.ReglaNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudMantenimientoTest {

    private SolicitudMantenimiento nuevaSolicitud() {
        Equipo equipo = new Equipo(1L, "Computador Dell", "Laboratorio", true);
        return new SolicitudMantenimiento(1L, equipo, "No enciende");
    }

    private Tecnico tecnico() {
        return new Tecnico(101L, "Juan Ríos", "Hardware");
    }

    @Test
    void alReportarFallaElEquipoQuedaFueraDeServicio() {
        SolicitudMantenimiento s = nuevaSolicitud();
        assertFalse(s.getEquipo().isEnServicio());
        assertEquals(EstadoSolicitud.PENDIENTE, s.getEstado());
    }

    @Test
    void laDescripcionEsObligatoria() {
        Equipo equipo = new Equipo(1L, "Teclado", "Lab", true);
        assertThrows(ReglaNegocioException.class, () -> new SolicitudMantenimiento(null, equipo, " "));
    }

    @Test
    void elTecnicoDebeTenerNombre() {
        assertThrows(ReglaNegocioException.class, () -> new Tecnico(1L, "", "Hardware"));
    }

    @Test
    void asignarTecnicoCambiaEstadoAAsignada() {
        SolicitudMantenimiento s = nuevaSolicitud();
        s.asignarTecnico(tecnico());
        assertEquals(EstadoSolicitud.ASIGNADA, s.getEstado());
        assertTrue(s.tieneTecnico());
    }

    @Test
    void noSePuedeFinalizarSinTecnico() {
        SolicitudMantenimiento s = nuevaSolicitud();
        ReglaNegocioException e = assertThrows(ReglaNegocioException.class, s::finalizar);
        assertEquals("No se puede finalizar sin técnico asignado", e.getMessage());
    }

    @Test
    void alFinalizarElEquipoVuelveAEstarEnServicio() {
        SolicitudMantenimiento s = nuevaSolicitud();
        s.asignarTecnico(tecnico());
        s.finalizar();
        assertEquals(EstadoSolicitud.FINALIZADA, s.getEstado());
        assertTrue(s.getEquipo().isEnServicio());
    }

    @Test
    void noSePuedeFinalizarDosVeces() {
        SolicitudMantenimiento s = nuevaSolicitud();
        s.asignarTecnico(tecnico());
        s.finalizar();
        assertThrows(ReglaNegocioException.class, s::finalizar);
    }

    @Test
    void noSePuedeAsignarTecnicoAUnaSolicitudFinalizada() {
        SolicitudMantenimiento s = nuevaSolicitud();
        s.asignarTecnico(tecnico());
        s.finalizar();
        Tecnico otro = tecnico();
        assertThrows(ReglaNegocioException.class, () -> s.asignarTecnico(otro));
    }

    @Test
    void elReporteIncluyeAlTecnico() {
        SolicitudMantenimiento s = nuevaSolicitud();
        s.asignarTecnico(tecnico());
        assertTrue(s.generarReporte().contains("Técnico: Juan Ríos"));
    }
}
