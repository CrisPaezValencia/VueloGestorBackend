package com.jcaa.hexagonal.adapter.databases.sql.mapper;

import com.jcaa.hexagonal.adapter.databases.sql.entity.UsuarioEntity;
import com.jcaa.hexagonal.core.domain.model.Rol;
import com.jcaa.hexagonal.core.domain.model.Usuario;

public final class UsuarioEntityMapper {

    private UsuarioEntityMapper() { }

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getClave(),
                Rol.from(entity.getRol())
        );
    }

    public static UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.id());
        entity.setNombre(usuario.nombre());
        entity.setClave(usuario.clave());
        entity.setRol(usuario.rol().name());
        return entity;
    }
}