package com.vecinosconectados.tareas_service.application.exception;

public class CampoRequeridoException extends RuntimeException {
    public CampoRequeridoException(String mensaje) {
        super(mensaje);
    }
}
