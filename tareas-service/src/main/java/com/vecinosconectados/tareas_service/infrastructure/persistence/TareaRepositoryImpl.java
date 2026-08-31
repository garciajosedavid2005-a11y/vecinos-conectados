package com.vecinosconectados.tareas_service.infrastructure.persistence;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Cierre con los casos de uso de la aplicación, implementando la interfaz TareaRepository.
@Repository
public class TareaRepositoryImpl implements TareaRepository {

    private final TareaJpaRepository jpaRepository;
    private final TareaMapper mapper;

    public TareaRepositoryImpl(TareaJpaRepository jpaRepository, TareaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Tarea guardar(Tarea tarea) {
        TareaEntity entity = mapper.aEntity(tarea);
        TareaEntity guardada = jpaRepository.save(entity);
        return mapper.aDominio(guardada);
    }

    @Override
    public Optional<Tarea> buscarPorId(String id) {
        return jpaRepository.findById(id)
                .map(mapper::aDominio);
    }

    @Override
    public List<Tarea> listarTodas() {
        return jpaRepository.findAll().stream()
                .map(mapper::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tarea> buscarPorEstado(EstadoTarea estado) {
        return jpaRepository.findByEstado(estado).stream()
                .map(mapper::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tarea> buscarPorPublicador(String publicadorId) {
        return jpaRepository.findByPublicadorId(publicadorId).stream()
                .map(mapper::aDominio)
                .collect(Collectors.toList());
    }
}
