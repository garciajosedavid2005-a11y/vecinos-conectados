package com.vecinosconectados.usuarios_service.domain.repository;

import com.vecinosconectados.usuarios_service.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(String id);

    Optional<Usuario> buscarPorEmail(String email);

    boolean existePorEmail(String email);
}
