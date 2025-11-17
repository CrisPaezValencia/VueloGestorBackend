package com.jcaa.hexagonal.entrypoint.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcaa.hexagonal.core.domain.model.Vuelo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record VueloRequest(
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate fechaCompra,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaSalida,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaLlegada,
        String agenciaViajes,
        String aerolinea,
        @NotBlank String numero,
        String estado,
        BigDecimal valor,
        Long clienteId,
        String puesto,
        Long avionId,
        Long aeropuertoSalidaId,
        Long aeropuertoLlegadaId,
        Long pilotoId
) {

    public Vuelo toDomain() {
        return new Vuelo(
                null,
                fechaCompra,
                fechaSalida,
                fechaLlegada,
                agenciaViajes,
                aerolinea,
                numero,
                estado,
                valor,
                clienteId,
                puesto,
                avionId,
                aeropuertoSalidaId,
                aeropuertoLlegadaId,
                pilotoId
        );
    }

    public Vuelo toDomainWithId(Long id) {
        return new Vuelo(
                id,
                fechaCompra,
                fechaSalida,
                fechaLlegada,
                agenciaViajes,
                aerolinea,
                numero,
                estado,
                valor,
                clienteId,
                puesto,
                avionId,
                aeropuertoSalidaId,
                aeropuertoLlegadaId,
                pilotoId
        );
    }
}