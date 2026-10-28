package com.mantenimiento.domain.model;

import com.mantenimiento.domain.exception.ReglaNegocioException;

public class SolicitudMantenimiento implements Reportable {

    private final Long id;
    private final Equipo equipo;
    private final String descripcion;
    private Tecnico tecnico;
    private EstadoSolicitud estado;

    public SolicitudMantenimiento(Long id, Equipo equipo, String descripcion) {
        if (equipo == null) {
            throw new ReglaNegocioException("El equipo es obligatorio");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new ReglaNegocioException("La descripción es obligatoria");
        }
        this.id = id;
        this.equipo = equipo;
        this.descripcion = descripcion;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.equipo.marcarFueraDeServicio();
    }

    public SolicitudMantenimiento(Long id, Equipo equipo, String descripcion,
                                  Tecnico tecnico, EstadoSolicitud estado) {
        this.id = id;
        this.equipo = equipo;
        this.descripcion = descripcion;
        this.tecnico = tecnico;
        this.estado = estado;
    }

    public void asignarTecnico(Tecnico tecnico) {
        if (tecnico == null) {
            throw new ReglaNegocioException("Debe indicar un técnico");
        }
        if (estaFinalizada()) {
            throw new ReglaNegocioException("La solicitud ya está finalizada");
        }
        this.tecnico = tecnico;
        this.estado = EstadoSolicitud.ASIGNADA;
    }

    public void finalizar() {
        if (estaFinalizada()) {
            throw new ReglaNegocioException("La solicitud ya está finalizada");
        }
        if (!tieneTecnico()) {
            throw new ReglaNegocioException("No se puede finalizar sin técnico asignado");
        }
        this.estado = EstadoSolicitud.FINALIZADA;
        this.equipo.ponerEnServicio();
    }

    public boolean tieneTecnico() {
        return tecnico != null;
    }

    public boolean estaFinalizada() {
        return estado == EstadoSolicitud.FINALIZADA;
    }

    @Override
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder()
                .append("===== REPORTE =====\n")
                .append("Solicitud: ").append(id).append("\n")
                .append("Equipo: ").append(equipo.getNombre()).append("\n")
                .append("Ubicación: ").append(equipo.getUbicacion()).append("\n")
                .append("Estado del equipo: ").append(equipo.getEstado()).append("\n")
                .append("Descripción: ").append(descripcion).append("\n")
                .append("Estado de la solicitud: ").append(estado).append("\n");

        if (tieneTecnico()) {
            reporte.append(tecnico.mostrarInformacion());
        } else {
            reporte.append("No hay técnico asignado.");
        }
        return reporte.toString();
    }

    public Long getId() {
        return id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }
}
