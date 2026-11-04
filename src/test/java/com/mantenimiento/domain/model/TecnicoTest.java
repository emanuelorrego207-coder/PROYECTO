package com.mantenimiento.domain.model;

import com.mantenimiento.domain.exception.ReglaNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// Pruebas unitarias de Jeronimo Velasquez
class TecnicoTest {

    @Test
    void elTecnicoGuardaSuNombre() {
        Tecnico tecnico = new Tecnico(1L, "Ana", "Redes");
        assertEquals("Ana", tecnico.getNombre());
    }

    @Test
    void elTecnicoGuardaSuEspecialidad() {
        Tecnico tecnico = new Tecnico(1L, "Ana", "Redes");
        assertEquals("Redes", tecnico.getEspecialidad());
    }

    @Test
    void elTecnicoMuestraSuInformacion() {
        Tecnico tecnico = new Tecnico(1L, "Ana", "Redes");
        assertEquals("Técnico: Ana - Especialidad: Redes", tecnico.mostrarInformacion());
    }

    @Test
    void unTecnicoSinNombreDaError() {
        assertThrows(ReglaNegocioException.class, () -> new Tecnico(1L, "", "Redes"));
    }
}
