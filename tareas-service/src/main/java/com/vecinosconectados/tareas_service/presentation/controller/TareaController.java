package com.vecinosconectados.tareas_service.presentation.controller;

import com.vecinosconectados.tareas_service.application.exception.CampoRequeridoException;
import com.vecinosconectados.tareas_service.application.usecase.*;
import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.presentation.dto.AsignarTareaRequest;
import com.vecinosconectados.tareas_service.presentation.dto.CrearTareaRequest;
import com.vecinosconectados.tareas_service.presentation.dto.TareaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final PublicarTareaUseCase publicarTareaUseCase;
    private final AsignarTareaUseCase asignarTareaUseCase;
    private final IniciarTareaUseCase iniciarTareaUseCase;
    private final CompletarTareaUseCase completarTareaUseCase;
    private final CancelarTareaUseCase cancelarTareaUseCase;
    private final ObtenerTareaUseCase obtenerTareaUseCase;
    private final ListarTareasUseCase listarTareasUseCase;

    @PostMapping
    public ResponseEntity<TareaResponse> crear(@Valid @RequestBody CrearTareaRequest request) {
        var comando = mapearComando(request);
        Tarea tarea = publicarTareaUseCase.ejecutar(comando);
        return ResponseEntity.status(HttpStatus.CREATED).body(TareaResponse.desde(tarea));
    }

    @PostMapping("/{id}/asignar")
    public ResponseEntity<TareaResponse> asignar(@PathVariable String id, @Valid @RequestBody AsignarTareaRequest request) {
        Tarea tarea = asignarTareaUseCase.ejecutar(id, request.getUsuarioId());
        return ResponseEntity.ok(TareaResponse.desde(tarea));
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<TareaResponse> iniciar(@PathVariable String id) {
        Tarea tarea = iniciarTareaUseCase.ejecutar(id);
        return ResponseEntity.ok(TareaResponse.desde(tarea));
    }

    @PostMapping("/{id}/completar")
    public ResponseEntity<TareaResponse> completar(@PathVariable String id) {
        Tarea tarea = completarTareaUseCase.ejecutar(id);
        return ResponseEntity.ok(TareaResponse.desde(tarea));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<TareaResponse> cancelar(@PathVariable String id) {
        Tarea tarea = cancelarTareaUseCase.ejecutar(id);
        return ResponseEntity.ok(TareaResponse.desde(tarea));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> obtener(@PathVariable String id) {
        Tarea tarea = obtenerTareaUseCase.ejecutar(id);
        return ResponseEntity.ok(TareaResponse.desde(tarea));
    }

    @GetMapping
    public ResponseEntity<List<TareaResponse>> listar(
            @RequestParam(required = false) EstadoTarea estado,
            @RequestParam(required = false) String publicadorId) {

        List<Tarea> tareas;
        if (estado != null) {
            tareas = listarTareasUseCase.porEstado(estado);
        } else if (publicadorId != null) {
            tareas = listarTareasUseCase.porPublicador(publicadorId);
        } else {
            tareas = listarTareasUseCase.todas();
        }

        List<TareaResponse> respuesta = tareas.stream()
                .map(TareaResponse::desde)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    private PublicarTareaComando mapearComando(CrearTareaRequest request) {
        var builder = PublicarTareaComando.builder()
                .titulo(request.getTitulo())
                .categoria(request.getCategoria())
                .publicadorId(request.getPublicadorId())
                .tipo(request.getTipo());

        switch (request.getTipo()) {
            case FISICA -> {
                if (request.getUbicacion() == null || request.getUbicacion().isBlank()) {
                    throw new CampoRequeridoException("ubicacion es obligatorio para tareas de tipo FISICA");
                }
                builder.ubicacion(request.getUbicacion());
            }
            case INTELECTUAL -> {
                if (request.getNivelDificultad() <= 0) {
                    throw new CampoRequeridoException("nivelDificultad es obligatorio para tareas de tipo INTELECTUAL");
                }
                builder.nivelDificultad(request.getNivelDificultad());
            }
            case MASCOTAS -> {
                if (request.getTipoMascota() == null || request.getTipoMascota().isBlank()) {
                    throw new CampoRequeridoException("tipoMascota es obligatorio para tareas de tipo MASCOTAS");
                }
                builder.tipoMascota(request.getTipoMascota());
            }
        }

        return builder.build();
    }
}
