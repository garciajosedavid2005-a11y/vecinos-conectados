package com.vecinosconectados.tareas_service.presentation.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private int status;
    private String error;
    private String mensaje;
    private LocalDateTime timestamp;
}