package com.vecinosconectados.tareas_service.infrastructure.config;

import com.vecinosconectados.tareas_service.application.usecase.*;
import com.vecinosconectados.tareas_service.domain.repository.TareaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PublicarTareaUseCase publicarTareaUseCase(TareaRepository tareaRepository) {
        return new PublicarTareaUseCase(tareaRepository);
    }

    @Bean
    public AsignarTareaUseCase asignarTareaUseCase(TareaRepository tareaRepository) {
        return new AsignarTareaUseCase(tareaRepository);
    }

    @Bean
    public CompletarTareaUseCase completarTareaUseCase(TareaRepository tareaRepository) {
        return new CompletarTareaUseCase(tareaRepository);
    }

    @Bean
    public CancelarTareaUseCase cancelarTareaUseCase(TareaRepository tareaRepository) {
        return new CancelarTareaUseCase(tareaRepository);
    }

    @Bean
    public ListarTareasUseCase listarTareasUseCase(TareaRepository tareaRepository) {
        return new ListarTareasUseCase(tareaRepository);
    }

    @Bean
    public IniciarTareaUseCase iniciarTareaUseCase(TareaRepository tareaRepository) {
        return new IniciarTareaUseCase(tareaRepository);
    }

    @Bean
    public ObtenerTareaUseCase obtenerTareaUseCase(TareaRepository tareaRepository) {
        return new ObtenerTareaUseCase(tareaRepository);
    }
}
