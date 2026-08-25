package com.vecinosconectados.tareas_service.application.exception;

public class TareaNoEncontradaException extends RuntimeException{
    public TareaNoEncontradaException(String id){
        super("No se encontró la tarea con id: " + id);
    }
}
