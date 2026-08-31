package com.vecinosconectados.tareas_service.domain.model;

import java.time.LocalDateTime;

import com.vecinosconectados.tareas_service.domain.strategy.RecompensaIntelectualStrategy;
import lombok.Getter;

@Getter
public class TareaIntelectual extends Tarea {

    private int nivelDificultad;

    public TareaIntelectual(String id, String titulo, String categoria, String publicadorId, int nivelDificultad) {
        super(id, titulo, categoria, publicadorId, new RecompensaIntelectualStrategy());
        this.nivelDificultad = nivelDificultad;
    }

    //Constructor específico para el mapeo de la base de datos
    public TareaIntelectual(String id, String titulo, String categoria, EstadoTarea estado, String publicadorId,
                        String asignadoId, LocalDateTime fechaPublicacion, int nivelDificultad) {
    super(id, titulo, categoria, estado, publicadorId, asignadoId, fechaPublicacion, new RecompensaIntelectualStrategy());
    this.nivelDificultad = nivelDificultad;
}

}
