package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.domain.model.TipoTarea;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PublicarTareaComando {
    private String titulo;
    private String categoria;
    private String publicadorId;
    private TipoTarea tipo;

    // Solo se usa si tipo = FISICA
    private String ubicacion;

    // Solo se usa si tipo = INTELECTUAL
    private int nivelDificultad;

    // Solo se usa si tipo = MASCOTAS
    private String tipoMascota;
}
