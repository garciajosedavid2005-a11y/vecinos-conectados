package com.vecinosconectados.tareas_service.domain.model;

import com.vecinosconectados.tareas_service.domain.strategy.RecompensaFisicaStrategy;
import lombok.Getter;


@Getter
public class TareaFisica extends Tarea {
    
    private String ubicacion;

    public TareaFisica(String id, String titulo, String categoria, String publicadorId, String ubicacion){
        super(id, titulo, categoria, publicadorId, new RecompensaFisicaStrategy());
        this.ubicacion = ubicacion;
    }

}
