package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.application.exception.TareaNoEncontradaException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsignarTareaUseCaseTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private AsignarTareaUseCase asignarTareaUseCase;

    @Test
    void debeAsignarUnaTareaPublicadaCorrectamente() {
        // Arrange
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");

        when(tareaRepository.buscarPorId("id-1")).thenReturn(Optional.of(tarea));
        when(tareaRepository.guardar(any(Tarea.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        // Act
        Tarea resultado = asignarTareaUseCase.ejecutar("id-1", "usuario-2");

        // Assert
        assertThat(resultado.getEstado().name()).isEqualTo("ASIGNADA");
        assertThat(resultado.getAsignadoId()).isEqualTo("usuario-2");
        verify(tareaRepository).guardar(tarea);
    }

    @Test
    void debeLanzarExcepcionSiLaTareaNoExiste() {
        // Arrange
        when(tareaRepository.buscarPorId("id-inexistente")).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> asignarTareaUseCase.ejecutar("id-inexistente", "usuario-2"))
                .isInstanceOf(TareaNoEncontradaException.class);

        verify(tareaRepository, never()).guardar(any());
    }

    @Test
    void debeLanzarExcepcionSiLaTareaYaEstaAsignada() {
        // Arrange
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");
        tarea.asignar("usuario-2"); // ya queda en estado ASIGNADA

        when(tareaRepository.buscarPorId("id-1")).thenReturn(Optional.of(tarea));

        // Act + Assert
        assertThatThrownBy(() -> asignarTareaUseCase.ejecutar("id-1", "usuario-3"))
                .isInstanceOf(TransicionEstadoInvalidaException.class)
                .hasMessageContaining("publicada");

        verify(tareaRepository, never()).guardar(any());
    }
}
