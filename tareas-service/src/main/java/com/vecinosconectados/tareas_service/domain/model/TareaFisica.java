package com.vecinosconectados.tareas_service.domain.model;

import java.time.LocalDateTime;

import com.vecinosconectados.tareas_service.domain.strategy.RecompensaFisicaStrategy;
import lombok.Getter;


@Getter
public class TareaFisica extends Tarea {
    
    private String ubicacion;

    public TareaFisica(String id, String titulo, String categoria, String publicadorId, String ubicacion){
        super(id, titulo, categoria, publicadorId, new RecompensaFisicaStrategy());
        this.ubicacion = ubicacion;
    }

    //Constructor específico para el mapeo de la base de datos
    public TareaFisica(String id, String titulo, String categoria, EstadoTarea estado, String publicadorId,
                    String asignadoId, LocalDateTime fechaPublicacion, String ubicacion) {
    super(id, titulo, categoria, estado, publicadorId, asignadoId, fechaPublicacion, new RecompensaFisicaStrategy());
    this.ubicacion = ubicacion;
}

}
