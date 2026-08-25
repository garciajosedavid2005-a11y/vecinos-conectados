package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.model.TareaFisica;
import com.vecinosconectados.tareas_service.domain.model.TareaIntelectual;
import com.vecinosconectados.tareas_service.domain.model.TareaMascotas;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;

import java.util.UUID;

public class PublicarTareaUseCase {

    private final TareaRepository tareaRepository;

    public PublicarTareaUseCase(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea ejecutar(PublicarTareaComando comando) {
        String id = UUID.randomUUID().toString();

        Tarea tarea = switch (comando.getTipo()) {
            case FISICA -> new TareaFisica(
                    id, comando.getTitulo(), comando.getCategoria(),
                    comando.getPublicadorId(), comando.getUbicacion());
            case INTELECTUAL -> new TareaIntelectual(
                    id, comando.getTitulo(), comando.getCategoria(),
                    comando.getPublicadorId(), comando.getNivelDificultad());
            case MASCOTAS -> new TareaMascotas(
                    id, comando.getTitulo(), comando.getCategoria(),
                    comando.getPublicadorId(), comando.getTipoMascota());
        };

        return tareaRepository.guardar(tarea);
    }
}
