package com.mantenimiento.domain.model;

import com.mantenimiento.domain.exception.ReglaNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de Tecnico.
 * Integrante: Jeronimo Velasquez
 */
class TecnicoTest {

    // Prueba de Jeronimo Velasquez: el técnico guarda su nombre
    @Test
    void elTecnicoGuardaSuNombre() {
        Tecnico tecnico = new Tecnico(1L, "Ana", "Redes");
        assertEquals("Ana", tecnico.getNombre());
    }

    // Prueba de Jeronimo Velasquez: el técnico guarda su especialidad
    @Test
    void elTecnicoGuardaSuEspecialidad() {
        Tecnico tecnico = new Tecnico(1L, "Ana", "Redes");
        assertEquals("Redes", tecnico.getEspecialidad());
    }

    // Prueba de Jeronimo Velasquez: el técnico muestra su nombre y especialidad
    @Test
    void elTecnicoMuestraSuInformacion() {
        Tecnico tecnico = new Tecnico(1L, "Ana", "Redes");
        assertEquals("Técnico: Ana - Especialidad: Redes", tecnico.mostrarInformacion());
    }

    // Prueba de Jeronimo Velasquez: no se puede crear un técnico sin nombre
    @Test
    void unTecnicoSinNombreDaError() {
        assertThrows(ReglaNegocioException.class, () -> new Tecnico(1L, "", "Redes"));
    }
}
