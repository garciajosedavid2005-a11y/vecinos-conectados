package com.vecinosconectados.tareas_service.infrastructure.persistence;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("INTELECTUAL")
@Getter
@NoArgsConstructor
public class TareaIntelectualEntity extends TareaEntity {

    private int nivelDificultad;

    public TareaIntelectualEntity(String id, String titulo, String categoria, EstadoTarea estado,
                                String publicadorId, String asignadoId, LocalDateTime fechaPublicacion,
                                int nivelDificultad) {
        super(id, titulo, categoria, estado, publicadorId, asignadoId, fechaPublicacion);
        this.nivelDificultad = nivelDificultad;
    }
}
