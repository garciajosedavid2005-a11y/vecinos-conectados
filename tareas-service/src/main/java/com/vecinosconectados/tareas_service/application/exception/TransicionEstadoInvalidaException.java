package com.vecinosconectados.tareas_service.application.exception;

public class TransicionEstadoInvalidaException extends RuntimeException {
    public TransicionEstadoInvalidaException(String message){
        super(message);
    }
    
}
