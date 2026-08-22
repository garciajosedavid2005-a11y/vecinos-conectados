package com.vecinosconectados.tareas_service.domain.strategy;

import com.vecinosconectados.tareas_service.domain.model.Tarea;

public interface CalculadoraRecompensa {
    int calcular(Tarea tarea);
}
