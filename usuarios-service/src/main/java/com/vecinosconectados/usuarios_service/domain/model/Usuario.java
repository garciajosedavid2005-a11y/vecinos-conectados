package com.vecinosconectados.usuarios_service.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Getter
public class Usuario {

    private static final Pattern PATRON_EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private String id;
    private String nombre;
    private String email;
    private String passwordHash;
    private Rol rol;
    private int reputacion;
    private LocalDateTime fechaRegistro;

    // Constructor para REGISTRAR un usuario nuevo
    public Usuario(String id, String nombre, String email, String passwordHash) {
        validarEmail(email);
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = Rol.USUARIO;
        this.reputacion = 0;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Constructor para RECONSTRUIR desde la base de datos
    public Usuario(String id, String nombre, String email, String passwordHash,
                    Rol rol, int reputacion, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.reputacion = reputacion;
        this.fechaRegistro = fechaRegistro;
    }

    private void validarEmail(String email) {
        if (email == null || !PATRON_EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del email no es valido: " + email);
        }
    }

    public void sumarPuntos(int puntos) {
    if (puntos <= 0) {
        throw new IllegalArgumentException("Los puntos a sumar deben ser mayores a 0");
    }
    this.reputacion += puntos;
}

    public void restarPuntos(int puntos) {
        if (puntos <= 0) {
            throw new IllegalArgumentException("Los puntos a restar deben ser mayores a 0");
        }
        this.reputacion = Math.max(0, this.reputacion - puntos);
    }
}