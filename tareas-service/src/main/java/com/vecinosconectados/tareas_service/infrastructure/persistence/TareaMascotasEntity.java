package com.vecinosconectados.tareas_service.infrastructure.persistence;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("MASCOTAS")
@Getter
@NoArgsConstructor
public class TareaMascotasEntity extends TareaEntity {

    private String tipoMascota;

    public TareaMascotasEntity(String id, String titulo, String categoria, EstadoTarea estado,
                                String publicadorId, String asignadoId, LocalDateTime fechaPublicacion,
                                String tipoMascota) {
        super(id, titulo, categoria, estado, publicadorId, asignadoId, fechaPublicacion);
        this.tipoMascota = tipoMascota;
    }
}