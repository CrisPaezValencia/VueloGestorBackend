package com.jcaa.hexagonal.core.domain.model;

public record Cliente(Long id, Long usuarioId, String email, String telefono) {
}