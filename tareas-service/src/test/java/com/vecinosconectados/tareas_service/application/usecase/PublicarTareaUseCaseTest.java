package com.vecinosconectados.tareas_service.application.usecase;

import com.vecinosconectados.tareas_service.domain.model.Tarea;
import com.vecinosconectados.tareas_service.domain.model.TipoTarea;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicarTareaUseCaseTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private PublicarTareaUseCase publicarTareaUseCase;

    @Test
    void debePublicarUnaTareaFisicaCorrectamente() {
        // Arrange (preparar)
        PublicarTareaComando comando = PublicarTareaComando.builder()
                .titulo("Ayuda con mudanza")
                .categoria("hogar")
                .publicadorId("usuario-1")
                .tipo(TipoTarea.FISICA)
                .ubicacion("Calle 45 #12-30")
                .build();

        when(tareaRepository.guardar(any(Tarea.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        // Act (ejecutar)
        Tarea resultado = publicarTareaUseCase.ejecutar(comando);

        // Assert (verificar)
        assertThat(resultado).isNotNull();
        assertThat(resultado.getTitulo()).isEqualTo("Ayuda con mudanza");
        assertThat(resultado.getEstado().name()).isEqualTo("PUBLICADA");
        assertThat(resultado.getPublicadorId()).isEqualTo("usuario-1");
    }
}