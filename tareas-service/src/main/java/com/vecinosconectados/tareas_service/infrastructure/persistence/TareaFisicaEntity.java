package com.vecinosconectados.tareas_service.infrastructure.persistence;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FISICA")
@Getter
@NoArgsConstructor
public class TareaFisicaEntity extends TareaEntity {

    private String ubicacion;

    public TareaFisicaEntity(String id, String titulo, String categoria, EstadoTarea estado,
                            String publicadorId, String asignadoId, LocalDateTime fechaPublicacion,
                            String ubicacion) {
        super(id, titulo, categoria, estado, publicadorId, asignadoId, fechaPublicacion);
        this.ubicacion = ubicacion;
    }
}