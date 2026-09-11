package com.vecinosconectados.tareas_service.presentation.dto;

import com.vecinosconectados.tareas_service.domain.model.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TareaResponse {

    private String id;
    private String titulo;
    private String categoria;
    private EstadoTarea estado;
    private String publicadorId;
    private String asignadoId;
    private LocalDateTime fechaPublicacion;
    private int recompensa;
    private String tipo;

    public static TareaResponse desde(Tarea tarea) {
        TareaResponseBuilder builder = TareaResponse.builder()
                .id(tarea.getId())
                .titulo(tarea.getTitulo())
                .categoria(tarea.getCategoria())
                .estado(tarea.getEstado())
                .publicadorId(tarea.getPublicadorId())
                .asignadoId(tarea.getAsignadoId())
                .fechaPublicacion(tarea.getFechaPublicacion())
                .recompensa(tarea.calcularRecompensa());

        if (tarea instanceof TareaFisica) {
            builder.tipo("FISICA");
        } else if (tarea instanceof TareaIntelectual) {
            builder.tipo("INTELECTUAL");
        } else if (tarea instanceof TareaMascotas) {
            builder.tipo("MASCOTAS");
        }

        return builder.build();
    }
}