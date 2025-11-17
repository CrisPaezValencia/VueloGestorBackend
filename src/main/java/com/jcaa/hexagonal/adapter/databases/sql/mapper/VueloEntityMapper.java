package com.jcaa.hexagonal.adapter.databases.sql.mapper;

import com.jcaa.hexagonal.adapter.databases.sql.entity.VueloEntity;
import com.jcaa.hexagonal.core.domain.model.Vuelo;

public final class VueloEntityMapper {

    private VueloEntityMapper() { }

    public static Vuelo toDomain(VueloEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Vuelo(
                entity.getId(),
                entity.getFechaCompra(),
                entity.getFechaSalida(),
                entity.getFechaLlegada(),
                entity.getAgenciaViajes(),
                entity.getAerolinea(),
                entity.getNumero(),
                entity.getEstado(),
                entity.getValor(),
                entity.getClienteId(),
                entity.getPuesto(),
                entity.getAvionId(),
                entity.getAeropuertoSalidaId(),
                entity.getAeropuertoLlegadaId(),
                entity.getPilotoId()
        );
    }

    public static VueloEntity toEntity(Vuelo vuelo) {
        VueloEntity entity = new VueloEntity();
        entity.setId(vuelo.id());
        entity.setFechaCompra(vuelo.fechaCompra());
        entity.setFechaSalida(vuelo.fechaSalida());
        entity.setFechaLlegada(vuelo.fechaLlegada());
        entity.setAgenciaViajes(vuelo.agenciaViajes());
        entity.setAerolinea(vuelo.aerolinea());
        entity.setNumero(vuelo.numero());
        entity.setEstado(vuelo.estado());
        entity.setValor(vuelo.valor());
        entity.setClienteId(vuelo.clienteId());
        entity.setPuesto(vuelo.puesto());
        entity.setAvionId(vuelo.avionId());
        entity.setAeropuertoSalidaId(vuelo.aeropuertoSalidaId());
        entity.setAeropuertoLlegadaId(vuelo.aeropuertoLlegadaId());
        entity.setPilotoId(vuelo.pilotoId());
        return entity;
    }
}