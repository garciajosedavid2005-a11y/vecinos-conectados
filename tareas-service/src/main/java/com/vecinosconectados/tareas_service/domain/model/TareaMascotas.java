package com.vecinosconectados.tareas_service.domain.model;

import com.vecinosconectados.tareas_service.domain.strategy.RecompensaMascotasStrategy;
import lombok.Getter;

@Getter
public class TareaMascotas extends Tarea {

    private String tipoMascota;

    public TareaMascotas(String id, String titulo, String categoria, String publicadorId, String tipoMascota) {
        super(id, titulo, categoria, publicadorId, new RecompensaMascotasStrategy());
        this.tipoMascota = tipoMascota;
    }
}