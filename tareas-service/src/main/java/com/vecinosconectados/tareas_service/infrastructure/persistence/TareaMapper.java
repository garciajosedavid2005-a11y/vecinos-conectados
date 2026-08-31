package com.vecinosconectados.tareas_service.infrastructure.persistence;

import org.springframework.stereotype.Component;

import com.vecinosconectados.tareas_service.domain.model.*;

//Mapeo entre la entidad en la base de datos y el modelo de dominio o lógica de negocio. 
//Se encarga de convertir entre las clases de entidad y las clases de dominio.
@Component
public class TareaMapper {

    public TareaEntity aEntity(Tarea tarea) {
        if (tarea instanceof TareaFisica t) {
            return new TareaFisicaEntity(
                    t.getId(), t.getTitulo(), t.getCategoria(), t.getEstado(),
                    t.getPublicadorId(), t.getAsignadoId(), t.getFechaPublicacion(),
                    t.getUbicacion());
        }
        if (tarea instanceof TareaIntelectual t) {
            return new TareaIntelectualEntity(
                    t.getId(), t.getTitulo(), t.getCategoria(), t.getEstado(),
                    t.getPublicadorId(), t.getAsignadoId(), t.getFechaPublicacion(),
                    t.getNivelDificultad());
        }
        if (tarea instanceof TareaMascotas t) {
            return new TareaMascotasEntity(
                    t.getId(), t.getTitulo(), t.getCategoria(), t.getEstado(),
                    t.getPublicadorId(), t.getAsignadoId(), t.getFechaPublicacion(),
                    t.getTipoMascota());
        }
        throw new IllegalArgumentException("Tipo de tarea no soportado: " + tarea.getClass());
    }

    public Tarea aDominio(TareaEntity entity) {
        if (entity instanceof TareaFisicaEntity e) {
            return new TareaFisica(
                    e.getId(), e.getTitulo(), e.getCategoria(), e.getEstado(),
                    e.getPublicadorId(), e.getAsignadoId(), e.getFechaPublicacion(),
                    e.getUbicacion());
        }
        if (entity instanceof TareaIntelectualEntity e) {
            return new TareaIntelectual(
                    e.getId(), e.getTitulo(), e.getCategoria(), e.getEstado(),
                    e.getPublicadorId(), e.getAsignadoId(), e.getFechaPublicacion(),
                    e.getNivelDificultad());
        }
        if (entity instanceof TareaMascotasEntity e) {
            return new TareaMascotas(
                    e.getId(), e.getTitulo(), e.getCategoria(), e.getEstado(),
                    e.getPublicadorId(), e.getAsignadoId(), e.getFechaPublicacion(),
                    e.getTipoMascota());
        }
        throw new IllegalArgumentException("Tipo de entidad no soportado: " + entity.getClass());
    }
}
