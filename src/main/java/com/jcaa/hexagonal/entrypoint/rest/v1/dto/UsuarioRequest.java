package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank String nombre,
        @NotBlank String clave,
        @NotBlank String rol
) { }