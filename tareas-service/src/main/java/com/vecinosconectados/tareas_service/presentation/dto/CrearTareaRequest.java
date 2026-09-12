package com.vecinosconectados.tareas_service.presentation.dto;

import com.vecinosconectados.tareas_service.domain.model.TipoTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CrearTareaRequest {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;

    @NotBlank(message = "El publicadorId es obligatorio")
    private String publicadorId;

    @NotNull(message = "El tipo de tarea es obligatorio")
    private TipoTarea tipo;

    // Solo requerido si tipo = FISICA
    private String ubicacion;

    // Solo requerido si tipo = INTELECTUAL
    private int nivelDificultad;

    // Solo requerido si tipo = MASCOTAS
    private String tipoMascota;
}
