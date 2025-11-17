package com.jcaa.hexagonal.core.port.in;

import com.jcaa.hexagonal.entrypoint.rest.v1.common.AuthenticatedUser;

public interface AuthUseCase {
    AuthenticatedUser login(String username, String password);
    String recordarClave(String username);
}