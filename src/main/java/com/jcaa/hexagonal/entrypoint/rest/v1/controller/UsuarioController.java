package com.jcaa.hexagonal.entrypoint.rest.v1.controller;

import com.jcaa.hexagonal.core.domain.model.Rol;
import com.jcaa.hexagonal.core.domain.model.Usuario;
import com.jcaa.hexagonal.core.port.in.UsuarioUseCase;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.UsuarioRequest;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest request) {
        Usuario created = usuarioUseCase.create(new Usuario(
                null,
                request.nombre(),
                request.clave(),
                Rol.from(request.rol())
        ));
        return ResponseEntity.created(URI.create("/api/v1/usuarios/" + created.id()))
                .body(toResponse(created));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll() {
        return ResponseEntity.ok(
                usuarioUseCase.findAll().stream().map(this::toResponse).toList()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        return usuarioUseCase.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody UsuarioRequest request) {
        Usuario updated = usuarioUseCase.update(id, new Usuario(
                id,
                request.nombre(),
                request.clave(),
                Rol.from(request.rol())
        ));
        return ResponseEntity.ok(toResponse(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.id(), usuario.nombre(), usuario.clave(), usuario.rol().name());
    }
}