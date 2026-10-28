package com.mantenimiento.domain.exception;

/**
 * Se lanza cuando no existe una solicitud con el id indicado.
 */
public class SolicitudNoEncontradaException extends RuntimeException {

    public SolicitudNoEncontradaException(Long id) {
        super("Solicitud no encontrada: " + id);
    }
}
