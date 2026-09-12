package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.application.exception.TransicionEstadoInvalidaException;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.model.TareaFisica;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IniciarTareaUseCaseTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private IniciarTareaUseCase iniciarTareaUseCase;

    @Test
    void debeIniciarUnaTareaAsignadaCorrectamente() {
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");
        tarea.asignar("usuario-2");

        when(tareaRepository.buscarPorId("id-1")).thenReturn(Optional.of(tarea));
        when(tareaRepository.guardar(any(Tarea.class))).thenAnswer(inv -> inv.getArgument(0));

        Tarea resultado = iniciarTareaUseCase.ejecutar("id-1");

        assertThat(resultado.getEstado().name()).isEqualTo("EN_PROGRESO");
    }

    @Test
    void debeLanzarExcepcionSiLaTareaSigueSoloPublicada() {
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");
        when(tareaRepository.buscarPorId("id-1")).thenReturn(Optional.of(tarea));

        assertThatThrownBy(() -> iniciarTareaUseCase.ejecutar("id-1"))
                .isInstanceOf(TransicionEstadoInvalidaException.class);
    }
}