package com.jcaa.hexagonal.adapter.databases.sql.mapper;

import com.jcaa.hexagonal.adapter.databases.sql.entity.ClienteEntity;
import com.jcaa.hexagonal.core.domain.model.Cliente;

public final class ClienteEntityMapper {

    private ClienteEntityMapper() { }

    public static Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Cliente(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getEmail(),
                entity.getTelefono()
        );
    }

    public static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.id());
        entity.setUsuarioId(cliente.usuarioId());
        entity.setEmail(cliente.email());
        entity.setTelefono(cliente.telefono());
        return entity;
    }
}