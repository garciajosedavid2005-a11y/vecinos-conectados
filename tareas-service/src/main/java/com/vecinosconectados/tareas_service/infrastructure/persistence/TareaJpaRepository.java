package com.vecinosconectados.tareas_service.infrastructure.persistence;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//Tecnologia JPA spring utilizada para la persistencia de datos.
//Obtiene un objeto tarea desde la base de datos, lo pasa al mapper para convertirlo a un objeto de dominio y luego lo devuelve al servicio de aplicación.
public interface TareaJpaRepository extends JpaRepository<TareaEntity, String> {

    List<TareaEntity> findByEstado(EstadoTarea estado);

    List<TareaEntity> findByPublicadorId(String publicadorId);
}