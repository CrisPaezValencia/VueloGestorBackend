package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import com.jcaa.hexagonal.core.domain.model.Piloto;

public record PilotoResponse(
        Long id,
        Long usuarioId,
        String licencia
) {
    public static PilotoResponse fromDomain(Piloto piloto) {
        return new PilotoResponse(
                piloto.id(),
                piloto.usuarioId(),
                piloto.licencia()
        );
    }
}