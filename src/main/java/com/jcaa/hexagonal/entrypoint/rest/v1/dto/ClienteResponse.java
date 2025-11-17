package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import com.jcaa.hexagonal.core.domain.model.Cliente;

public record ClienteResponse(
        Long id,
        Long usuarioId,
        String email,
        String telefono
) {
    public static ClienteResponse fromDomain(Cliente cliente) {
        return new ClienteResponse(
                cliente.id(),
                cliente.usuarioId(),
                cliente.email(),
                cliente.telefono()
        );
    }
}