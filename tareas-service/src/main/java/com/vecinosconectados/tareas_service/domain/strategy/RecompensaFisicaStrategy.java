package com.vecinosconectados.tareas_service.domain.strategy;

import com.vecinosconectados.tareas_service.domain.model.Tarea;

public class RecompensaFisicaStrategy implements CalculadoraRecompensa {

    private static final int PUNTOS_BASE = 15;

    @Override
    public int calcular(Tarea tarea) {
        return PUNTOS_BASE;
    }
}
