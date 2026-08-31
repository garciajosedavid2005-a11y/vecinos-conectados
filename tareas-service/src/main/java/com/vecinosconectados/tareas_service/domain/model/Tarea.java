package com.vecinosconectados.tareas_service.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;
import com.vecinosconectados.tareas_service.domain.strategy.CalculadoraRecompensa;

@Getter
public abstract class Tarea {
    private String id;
    private String titulo;
    private String categoria;
    private EstadoTarea estado;
    private String publicadorId;
    private String asignadoId;
    private LocalDateTime fechaPublicacion;
    private CalculadoraRecompensa calculadoraRecompensa;

    protected Tarea(String id, String titulo, String categoria, String publicadorId, CalculadoraRecompensa calculadoraRecompensa) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.publicadorId = publicadorId;
        this.calculadoraRecompensa = calculadoraRecompensa;
        this.estado = EstadoTarea.PUBLICADA;
        this.fechaPublicacion = LocalDateTime.now();
    }

    //Constructor específico para el mapeo de la base de datos
    protected Tarea(String id, String titulo, String categoria, EstadoTarea estado, String publicadorId,
                    String asignadoId, LocalDateTime fechaPublicacion, CalculadoraRecompensa calculadoraRecompensa) {
    this.id = id;
    this.titulo = titulo;
    this.categoria = categoria;
    this.estado = estado;
    this.publicadorId = publicadorId;
    this.asignadoId = asignadoId;
    this.fechaPublicacion = fechaPublicacion;
    this.calculadoraRecompensa = calculadoraRecompensa;
}

    public void asignar(String usuarioId) {
        if (this.estado != EstadoTarea.PUBLICADA) {
            throw new IllegalStateException("Solo se puede asignar una tarea que este publicada");
        }
        this.asignadoId = usuarioId;
        this.estado = EstadoTarea.ASIGNADA;
    }

    public void iniciar() {
        if (this.estado != EstadoTarea.ASIGNADA) {
            throw new IllegalStateException("Solo se puede iniciar una tarea que este asignada");
        }
        this.estado = EstadoTarea.EN_PROGRESO;
    }

    public void completar() {
        if (this.estado != EstadoTarea.EN_PROGRESO) {
            throw new IllegalStateException("Solo se puede completar una tarea que este en progreso");
        }
        this.estado = EstadoTarea.COMPLETADA;
    }

    public void cancelar() {
        if (this.estado == EstadoTarea.COMPLETADA) {
            throw new IllegalStateException("No se puede cancelar una tarea ya completada");
        }
        this.estado = EstadoTarea.CANCELADA;
    }

    public int calcularRecompensa() {
        return calculadoraRecompensa.calcular(this);
    }
}





