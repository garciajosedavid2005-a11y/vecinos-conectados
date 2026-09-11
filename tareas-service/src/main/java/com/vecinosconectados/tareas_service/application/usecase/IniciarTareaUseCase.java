package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.application.exception.TareaNoEncontradaException;
import com.vecinosconectados.tareas_service.application.exception.TransicionEstadoInvalidaException;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;

public class IniciarTareaUseCase {

    private final TareaRepository tareaRepository;

    public IniciarTareaUseCase(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea ejecutar(String tareaId) {
        Tarea tarea = tareaRepository.buscarPorId(tareaId)
                .orElseThrow(() -> new TareaNoEncontradaException(tareaId));
        try {
            tarea.iniciar();
        } catch (IllegalStateException e) {
            throw new TransicionEstadoInvalidaException(e.getMessage());
        }
        return tareaRepository.guardar(tarea);
    }
}