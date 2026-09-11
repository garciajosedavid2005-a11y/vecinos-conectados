package com.vecinosconectados.tareas_service.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AsignarTareaRequest {

    @NotBlank(message = "El usuarioId es obligatorio")
    private String usuarioId;
}