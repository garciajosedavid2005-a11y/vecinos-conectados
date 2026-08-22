package com.vecinosconectados.tareas_service.domain.repository;

import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;

import java.util.List;
import java.util.Optional;

public interface TareaRepository {

    Tarea guardar(Tarea tarea);

    Optional<Tarea> buscarPorId(String id);

    List<Tarea> listarTodas();

    List<Tarea> buscarPorEstado(EstadoTarea estado);

    List<Tarea> buscarPorPublicador(String publicadorId);
}
