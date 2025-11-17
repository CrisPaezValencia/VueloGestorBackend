package com.jcaa.hexagonal.entrypoint.rest.v1.controller;

import com.jcaa.hexagonal.core.domain.model.Cliente;
import com.jcaa.hexagonal.core.port.in.ClienteUseCase;
import com.jcaa.hexagonal.entrypoint.rest.v1.common.AuthenticatedUser;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.ClienteRequest;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest request) {
        Cliente created = clienteUseCase.create(request.toDomain(null));
        return ResponseEntity.created(URI.create("/api/v1/clientes/" + created.id()))
                .body(ClienteResponse.fromDomain(created));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> findAll() {
        return ResponseEntity.ok(
                clienteUseCase.findAll().stream()
                        .map(ClienteResponse::fromDomain)
                        .toList()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable("id") Long id) {
        return clienteUseCase.findById(id)
                .map(ClienteResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping("/me")
    public ResponseEntity<ClienteResponse> me() {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (auth.getRol().name().equals("CLIENTE")) {
            return clienteUseCase.findByUsuarioId(auth.getUsuarioId())
                    .map(ClienteResponse::fromDomain)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.status(403).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody ClienteRequest request) {
        Cliente updated = clienteUseCase.update(id, request.toDomain(id));
        return ResponseEntity.ok(ClienteResponse.fromDomain(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        clienteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}