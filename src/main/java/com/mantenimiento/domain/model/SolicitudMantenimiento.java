package com.mantenimiento.domain.model;

public class SolicitudMantenimiento implements Reportable {

    private Long id;
    private Equipo equipo;
    private String descripcion;
    private Tecnico tecnico;
    private EstadoSolicitud estado;

    public SolicitudMantenimiento(Long id, Equipo equipo, String descripcion) {
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
        if (estado == EstadoSolicitud.FINALIZADA) {
            throw new IllegalStateException("La solicitud ya está finalizada");
        }
        this.tecnico = tecnico;
        this.estado = EstadoSolicitud.ASIGNADA;
    }

    public void finalizar() {
        if (tecnico == null) {
            throw new IllegalStateException("No se puede finalizar sin técnico asignado");
        }
        if (estado == EstadoSolicitud.FINALIZADA) {
            throw new IllegalStateException("La solicitud ya está finalizada");
        }
        this.estado = EstadoSolicitud.FINALIZADA;
        this.equipo.ponerEnServicio();
    }

    @Override
    public String generarReporte() {
        String reporte = "===== REPORTE =====\n"
                + "Solicitud: " + id + "\n"
                + "Equipo: " + equipo.getNombre() + "\n"
                + "Ubicación: " + equipo.getUbicacion() + "\n"
                + "Estado del equipo: " + equipo.getEstado() + "\n"
                + "Descripción: " + descripcion + "\n"
                + "Estado de la solicitud: " + estado + "\n";

        if (tecnico != null) {
            reporte += tecnico.mostrarInformacion();
        } else {
            reporte += "No hay técnico asignado.";
        }
        return reporte;
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
