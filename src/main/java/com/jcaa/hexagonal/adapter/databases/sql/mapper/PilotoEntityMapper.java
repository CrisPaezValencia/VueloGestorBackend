package com.jcaa.hexagonal.adapter.databases.sql.mapper;

import com.jcaa.hexagonal.adapter.databases.sql.entity.PilotoEntity;
import com.jcaa.hexagonal.core.domain.model.Piloto;

public final class PilotoEntityMapper {

    private PilotoEntityMapper() { }

    public static Piloto toDomain(PilotoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Piloto(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getLicencia()
        );
    }

    public static PilotoEntity toEntity(Piloto piloto) {
        PilotoEntity entity = new PilotoEntity();
        entity.setId(piloto.id());
        entity.setUsuarioId(piloto.usuarioId());
        entity.setLicencia(piloto.licencia());
        return entity;
    }
}