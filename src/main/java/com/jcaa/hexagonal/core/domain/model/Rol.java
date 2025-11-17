package com.jcaa.hexagonal.core.domain.model;

public enum Rol {
    ADMIN, PILOTO, CLIENTE;

    public static Rol from(String value) {
        for (Rol rol : values()) {
            if (rol.name().equalsIgnoreCase(value)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no soportado: " + value);
    }
}