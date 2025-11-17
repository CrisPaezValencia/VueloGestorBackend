package com.jcaa.hexagonal.core.service;

import com.jcaa.hexagonal.core.domain.model.Cliente;
import com.jcaa.hexagonal.core.domain.model.Piloto;
import com.jcaa.hexagonal.core.domain.model.Rol;
import com.jcaa.hexagonal.core.domain.model.Usuario;
import com.jcaa.hexagonal.core.port.in.AuthUseCase;
import com.jcaa.hexagonal.core.port.in.ClienteUseCase;
import com.jcaa.hexagonal.core.port.in.PilotoUseCase;
import com.jcaa.hexagonal.core.port.in.UsuarioUseCase;
import com.jcaa.hexagonal.entrypoint.rest.v1.common.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {

    private final UsuarioUseCase usuarioUseCase;
    private final ClienteUseCase clienteUseCase;
    private final PilotoUseCase pilotoUseCase;

    public AuthService(UsuarioUseCase usuarioUseCase,
                       ClienteUseCase clienteUseCase,
                       PilotoUseCase pilotoUseCase) {
        this.usuarioUseCase = usuarioUseCase;
        this.clienteUseCase = clienteUseCase;
        this.pilotoUseCase = pilotoUseCase;
    }

    @Override
    public AuthenticatedUser login(String username, String password) {
        Usuario usuario = usuarioUseCase.findByNombre(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario o clave incorrecta"));

        if (!usuario.clave().equals(password)) {
            throw new IllegalArgumentException("Usuario o clave incorrecta");
        }

        Long clienteId = null;
        Long pilotoId = null;

        if (usuario.rol() == Rol.CLIENTE) {
            clienteId = clienteUseCase.findByUsuarioId(usuario.id())
                    .map(Cliente::id)
                    .orElse(null);
        } else if (usuario.rol() == Rol.PILOTO) {
            pilotoId = pilotoUseCase.findByUsuarioId(usuario.id())
                    .map(Piloto::id)
                    .orElse(null);
        }

        return new AuthenticatedUser(
                usuario.id(),
                usuario.nombre(),
                usuario.rol(),
                clienteId,
                pilotoId
        );
    }

    @Override
    public String recordarClave(String username) {
        Usuario usuario = usuarioUseCase.findByNombre(username)
                .orElseThrow(() -> new IllegalArgumentException("No existe un usuario con ese nombre"));
        return usuario.clave();
    }
}