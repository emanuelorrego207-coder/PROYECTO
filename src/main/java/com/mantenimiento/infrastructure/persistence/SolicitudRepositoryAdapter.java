package com.mantenimiento.infrastructure.persistence;

import com.mantenimiento.domain.exception.SolicitudNoEncontradaException;
import com.mantenimiento.domain.model.Equipo;
import com.mantenimiento.domain.model.EstadoSolicitud;
import com.mantenimiento.domain.model.SolicitudMantenimiento;
import com.mantenimiento.domain.model.Tecnico;
import com.mantenimiento.domain.port.SolicitudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolicitudRepositoryAdapter implements SolicitudRepository {

    private final SpringDataSolicitudRepository repository;

    public SolicitudRepositoryAdapter(SpringDataSolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    public SolicitudMantenimiento guardar(SolicitudMantenimiento solicitud) {
        SolicitudEntity entity = convertirAEntity(solicitud);
        SolicitudEntity guardada = repository.save(entity);
        return convertirADominio(guardada);
    }

    @Override
    public SolicitudMantenimiento buscarPorId(Long id) {
        SolicitudEntity entity = repository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException(id));
        return convertirADominio(entity);
    }

    @Override
    public List<SolicitudMantenimiento> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirADominio)
                .toList();
    }

    private SolicitudEntity convertirAEntity(SolicitudMantenimiento s) {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setId(s.getId());
        entity.setDescripcion(s.getDescripcion());
        entity.setEstado(s.getEstado().name());

        entity.setEquipoId(s.getEquipo().getId());
        entity.setEquipoNombre(s.getEquipo().getNombre());
        entity.setEquipoUbicacion(s.getEquipo().getUbicacion());
        entity.setEquipoEnServicio(s.getEquipo().isEnServicio());

        if (s.tieneTecnico()) {
            entity.setTecnicoId(s.getTecnico().getId());
            entity.setTecnicoNombre(s.getTecnico().getNombre());
            entity.setTecnicoEspecialidad(s.getTecnico().getEspecialidad());
        }
        return entity;
    }

    private SolicitudMantenimiento convertirADominio(SolicitudEntity e) {
        Equipo equipo = new Equipo(
                e.getEquipoId(),
                e.getEquipoNombre(),
                e.getEquipoUbicacion(),
                e.isEquipoEnServicio());

        Tecnico tecnico = null;
        if (e.getTecnicoNombre() != null && !e.getTecnicoNombre().isBlank()) {
            tecnico = new Tecnico(e.getTecnicoId(), e.getTecnicoNombre(), e.getTecnicoEspecialidad());
        }

        return new SolicitudMantenimiento(
                e.getId(),
                equipo,
                e.getDescripcion(),
                tecnico,
                EstadoSolicitud.valueOf(e.getEstado()));
    }
}
