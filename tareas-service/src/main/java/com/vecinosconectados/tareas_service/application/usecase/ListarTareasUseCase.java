package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;

import java.util.List;

public class ListarTareasUseCase {

    private final TareaRepository tareaRepository;

    public ListarTareasUseCase(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> todas() {
        return tareaRepository.listarTodas();
    }

    public List<Tarea> porEstado(EstadoTarea estado) {
        return tareaRepository.buscarPorEstado(estado);
    }

    public List<Tarea> porPublicador(String publicadorId) {
        return tareaRepository.buscarPorPublicador(publicadorId);
    }
}
