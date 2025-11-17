package com.jcaa.hexagonal.entrypoint.rest.v1.auth.dto;

public record LoginResponse(
        Long usuarioId,
        String username,
        String rol,
        Long clienteId,
        Long pilotoId
) { }