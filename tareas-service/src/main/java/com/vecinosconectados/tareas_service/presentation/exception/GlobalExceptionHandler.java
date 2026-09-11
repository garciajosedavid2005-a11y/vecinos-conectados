package com.vecinosconectados.tareas_service.presentation.exception;

import com.vecinosconectados.tareas_service.application.exception.CampoRequeridoException;
import com.vecinosconectados.tareas_service.application.exception.TareaNoEncontradaException;
import com.vecinosconectados.tareas_service.application.exception.TransicionEstadoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TareaNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> manejarTareaNoEncontrada(TareaNoEncontradaException e) {
        return construirRespuesta(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(TransicionEstadoInvalidaException.class)
    public ResponseEntity<ErrorResponse> manejarTransicionInvalida(TransicionEstadoInvalidaException e) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(CampoRequeridoException.class)
    public ResponseEntity<ErrorResponse> manejarCampoRequerido(CampoRequeridoException e) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((a, b) -> a + " | " + b)
                .orElse("Datos invalidos");
        return construirRespuesta(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErrorGeneral(Exception e) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
    }

    private ResponseEntity<ErrorResponse> construirRespuesta(HttpStatus status, String mensaje) {
        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .mensaje(mensaje)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(error);
    }
}