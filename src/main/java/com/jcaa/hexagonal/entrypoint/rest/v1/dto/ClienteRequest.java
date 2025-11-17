package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import com.jcaa.hexagonal.core.domain.model.Cliente;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
        @NotNull Long usuarioId,
        String email,
        String telefono
) {
    public Cliente toDomain(Long id) {
        return new Cliente(id, usuarioId, email, telefono);
    }
}