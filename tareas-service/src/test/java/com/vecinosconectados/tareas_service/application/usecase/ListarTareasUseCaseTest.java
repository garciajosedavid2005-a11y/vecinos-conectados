package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.domain.model.EstadoTarea;
import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.model.TareaFisica;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarTareasUseCaseTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private ListarTareasUseCase listarTareasUseCase;

    @Test
    void debeDevolverTodasLasTareas() {
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");
        when(tareaRepository.listarTodas()).thenReturn(List.of(tarea));

        List<Tarea> resultado = listarTareasUseCase.todas();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getId()).isEqualTo("id-1");
    }

    @Test
    void debeDevolverTareasFiltradasPorEstado() {
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");
        when(tareaRepository.buscarPorEstado(EstadoTarea.PUBLICADA)).thenReturn(List.of(tarea));

        List<Tarea> resultado = listarTareasUseCase.porEstado(EstadoTarea.PUBLICADA);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getEstado()).isEqualTo(EstadoTarea.PUBLICADA);
    }

    @Test
    void debeDevolverTareasFiltradasPorPublicador() {
        Tarea tarea = new TareaFisica("id-1", "Ayuda con mudanza", "hogar", "usuario-1", "Calle 45");
        when(tareaRepository.buscarPorPublicador("usuario-1")).thenReturn(List.of(tarea));

        List<Tarea> resultado = listarTareasUseCase.porPublicador("usuario-1");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getPublicadorId()).isEqualTo("usuario-1");
    }

    @Test
    void debeDevolverListaVaciaSiNoHayTareas() {
        when(tareaRepository.listarTodas()).thenReturn(List.of());

        List<Tarea> resultado = listarTareasUseCase.todas();

        assertThat(resultado).isEmpty();
    }
}
