package com.mantenimiento.application.service;

import com.mantenimiento.domain.model.Equipo;
import com.mantenimiento.domain.model.SolicitudMantenimiento;
import com.mantenimiento.domain.model.Tecnico;
import com.mantenimiento.domain.port.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    public SolicitudMantenimiento reportarFalla(Long equipoId, String equipoNombre,
                                                String ubicacion, String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        Equipo equipo = new Equipo(equipoId, equipoNombre, ubicacion, true);
        SolicitudMantenimiento solicitud = new SolicitudMantenimiento(null, equipo, descripcion);
        return repository.guardar(solicitud);
    }

    public List<SolicitudMantenimiento> listar() {
        return repository.listar();
    }

    public SolicitudMantenimiento asignarTecnico(Long id, Long tecnicoId,
                                                 String nombre, String especialidad) {
        SolicitudMantenimiento solicitud = repository.buscarPorId(id);
        solicitud.asignarTecnico(new Tecnico(tecnicoId, nombre, especialidad));
        return repository.guardar(solicitud);
    }

    public SolicitudMantenimiento finalizar(Long id) {
        SolicitudMantenimiento solicitud = repository.buscarPorId(id);
        solicitud.finalizar();
        return repository.guardar(solicitud);
    }

    public String generarReporte(Long id) {
        return repository.buscarPorId(id).generarReporte();
    }
}
