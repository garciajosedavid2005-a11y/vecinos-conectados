package com.vecinosconectados.tareas_service.domain.model;

import java.time.LocalDateTime;

import com.vecinosconectados.tareas_service.domain.strategy.RecompensaMascotasStrategy;
import lombok.Getter;

@Getter
public class TareaMascotas extends Tarea {

    private String tipoMascota;

    public TareaMascotas(String id, String titulo, String categoria, String publicadorId, String tipoMascota) {
        super(id, titulo, categoria, publicadorId, new RecompensaMascotasStrategy());
        this.tipoMascota = tipoMascota;
    }

    //Constructor específico para el mapeo de la base de datos
    public TareaMascotas(String id, String titulo, String categoria, EstadoTarea estado, String publicadorId,
                    String asignadoId, LocalDateTime fechaPublicacion, String tipoMascota) {
    super(id, titulo, categoria, estado, publicadorId, asignadoId, fechaPublicacion, new RecompensaMascotasStrategy());
    this.tipoMascota = tipoMascota;
}
}