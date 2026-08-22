package com.vecinosconectados.tareas_service.domain.model;

import com.vecinosconectados.tareas_service.domain.strategy.RecompensaIntelectualStrategy;
import lombok.Getter;

@Getter
public class TareaIntelectual extends Tarea {

    private int nivelDificultad;

    public TareaIntelectual(String id, String titulo, String categoria, String publicadorId, int nivelDificultad) {
        super(id, titulo, categoria, publicadorId, new RecompensaIntelectualStrategy());
        this.nivelDificultad = nivelDificultad;
    }

}
