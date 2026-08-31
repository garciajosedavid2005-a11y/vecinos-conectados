package com.vecinosconectados.tareas_service.infrastructure.persistence;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_tarea")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class TareaEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTarea estado;

    @Column(name = "publicador_id", nullable = false)
    private String publicadorId;

    @Column(name = "asignado_id")
    private String asignadoId;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    protected TareaEntity(String id, String titulo, String categoria, EstadoTarea estado,
                            String publicadorId, String asignadoId, LocalDateTime fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.estado = estado;
        this.publicadorId = publicadorId;
        this.asignadoId = asignadoId;
        this.fechaPublicacion = fechaPublicacion;
    }
}