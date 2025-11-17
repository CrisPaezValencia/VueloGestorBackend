package com.jcaa.hexagonal.core.domain.model;

public record Usuario(Long id, String nombre, String clave, Rol rol) {
}