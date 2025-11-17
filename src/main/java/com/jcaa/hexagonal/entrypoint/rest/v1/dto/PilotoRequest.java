package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import com.jcaa.hexagonal.core.domain.model.Piloto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PilotoRequest(
        @NotNull Long usuarioId,
        @NotBlank String licencia
) {
    public Piloto toDomain(Long id) {
        return new Piloto(id, usuarioId, licencia);
    }
}