package com.jcaa.hexagonal.entrypoint.rest.v1.auth.dto;

public record RememberPasswordResponse(
        String username,
        String password
) { }