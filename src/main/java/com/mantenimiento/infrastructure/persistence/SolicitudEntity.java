package com.mantenimiento.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitudes")
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String estado;

    private Long equipoId;
    private String equipoNombre;
    private String equipoUbicacion;
    private boolean equipoEnServicio;

    private Long tecnicoId;
    private String tecnicoNombre;
    private String tecnicoEspecialidad;

    public SolicitudEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getEquipoId() { return equipoId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }

    public String getEquipoNombre() { return equipoNombre; }
    public void setEquipoNombre(String equipoNombre) { this.equipoNombre = equipoNombre; }

    public String getEquipoUbicacion() { return equipoUbicacion; }
    public void setEquipoUbicacion(String equipoUbicacion) { this.equipoUbicacion = equipoUbicacion; }

    public boolean isEquipoEnServicio() { return equipoEnServicio; }
    public void setEquipoEnServicio(boolean equipoEnServicio) { this.equipoEnServicio = equipoEnServicio; }

    public Long getTecnicoId() { return tecnicoId; }
    public void setTecnicoId(Long tecnicoId) { this.tecnicoId = tecnicoId; }

    public String getTecnicoNombre() { return tecnicoNombre; }
    public void setTecnicoNombre(String tecnicoNombre) { this.tecnicoNombre = tecnicoNombre; }

    public String getTecnicoEspecialidad() { return tecnicoEspecialidad; }
    public void setTecnicoEspecialidad(String tecnicoEspecialidad) { this.tecnicoEspecialidad = tecnicoEspecialidad; }
}
