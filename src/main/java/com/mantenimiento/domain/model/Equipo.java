package com.mantenimiento.domain.model;

public class Equipo {

    private Long id;
    private String nombre;
    private String ubicacion;
    private boolean enServicio;

    public Equipo(Long id, String nombre, String ubicacion, boolean enServicio) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.enServicio = enServicio;
    }

    public void marcarFueraDeServicio() {
        this.enServicio = false;
    }

    public void ponerEnServicio() {
        this.enServicio = true;
    }

    public String getEstado() {
        if (enServicio) {
            return "En servicio";
        }
        return "Fuera de servicio";
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public boolean isEnServicio() {
        return enServicio;
    }
}
