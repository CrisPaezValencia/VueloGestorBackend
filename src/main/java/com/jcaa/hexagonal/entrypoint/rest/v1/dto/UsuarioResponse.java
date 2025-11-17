package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

public record UsuarioResponse(
        Long id,
        String nombre,
        String clave,
        String rol
) { }