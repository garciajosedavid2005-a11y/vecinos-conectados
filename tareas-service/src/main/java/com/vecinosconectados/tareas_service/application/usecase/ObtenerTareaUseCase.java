package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.application.exception.TareaNoEncontradaException;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;

public class ObtenerTareaUseCase {

    private final TareaRepository tareaRepository;

    public ObtenerTareaUseCase(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea ejecutar(String tareaId) {
        return tareaRepository.buscarPorId(tareaId)
                .orElseThrow(() -> new TareaNoEncontradaException(tareaId));
    }
}
