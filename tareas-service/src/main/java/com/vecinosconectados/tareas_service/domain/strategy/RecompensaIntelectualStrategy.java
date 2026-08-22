package com.vecinosconectados.tareas_service.domain.strategy;

import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.model.TareaIntelectual;

public class RecompensaIntelectualStrategy implements CalculadoraRecompensa {

    private static final int PUNTOS_POR_NIVEL = 5;

    @Override
    public int calcular(Tarea tarea) {
        if (tarea instanceof TareaIntelectual tareaIntelectual) {
            return tareaIntelectual.getNivelDificultad() * PUNTOS_POR_NIVEL;
        }
        throw new IllegalArgumentException("Esta estrategia solo aplica a TareaIntelectual");
    }
}