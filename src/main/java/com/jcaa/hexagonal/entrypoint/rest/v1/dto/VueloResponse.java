package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcaa.hexagonal.core.domain.model.Vuelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record VueloResponse(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate fechaCompra,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaSalida,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaLlegada,
        String agenciaViajes,
        String aerolinea,
        String numero,
        String estado,
        BigDecimal valor,
        Long clienteId,
        String puesto,
        Long avionId,
        Long aeropuertoSalidaId,
        Long aeropuertoLlegadaId,
        Long pilotoId
) {
    public static VueloResponse fromDomain(Vuelo vuelo) {
        return new VueloResponse(
                vuelo.id(),
                vuelo.fechaCompra(),
                vuelo.fechaSalida(),
                vuelo.fechaLlegada(),
                vuelo.agenciaViajes(),
                vuelo.aerolinea(),
                vuelo.numero(),
                vuelo.estado(),
                vuelo.valor(),
                vuelo.clienteId(),
                vuelo.puesto(),
                vuelo.avionId(),
                vuelo.aeropuertoSalidaId(),
                vuelo.aeropuertoLlegadaId(),
                vuelo.pilotoId()
        );
    }
}