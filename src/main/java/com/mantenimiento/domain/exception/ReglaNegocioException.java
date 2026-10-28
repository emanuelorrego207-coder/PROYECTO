package com.mantenimiento.domain.exception;

/**
 * Se lanza cuando una operación incumple una regla de negocio del dominio.
 */
public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
