package com.jcaa.hexagonal.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Vuelo(
        Long id,
        LocalDate fechaCompra,
        LocalDateTime fechaSalida,
        LocalDateTime fechaLlegada,
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
}