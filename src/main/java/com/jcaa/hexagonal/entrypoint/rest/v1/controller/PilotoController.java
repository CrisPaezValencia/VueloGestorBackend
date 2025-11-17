package com.jcaa.hexagonal.entrypoint.rest.v1.controller;

import com.jcaa.hexagonal.core.domain.model.Piloto;
import com.jcaa.hexagonal.core.port.in.PilotoUseCase;
import com.jcaa.hexagonal.entrypoint.rest.v1.common.AuthenticatedUser;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.PilotoRequest;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.PilotoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pilotos")
public class PilotoController {

    private final PilotoUseCase pilotoUseCase;

    public PilotoController(PilotoUseCase pilotoUseCase) {
        this.pilotoUseCase = pilotoUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PilotoResponse> create(@Valid @RequestBody PilotoRequest request) {
        Piloto created = pilotoUseCase.create(request.toDomain(null));
        return ResponseEntity.created(URI.create("/api/v1/pilotos/" + created.id()))
                .body(PilotoResponse.fromDomain(created));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PilotoResponse>> findAll() {
        return ResponseEntity.ok(
                pilotoUseCase.findAll().stream()
                        .map(PilotoResponse::fromDomain)
                        .toList()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PilotoResponse> findById(@PathVariable("id") Long id) {
        return pilotoUseCase.findById(id)
                .map(PilotoResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','PILOTO')")
    @GetMapping("/me")
    public ResponseEntity<PilotoResponse> me() {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (auth.getRol().name().equals("PILOTO")) {
            return pilotoUseCase.findByUsuarioId(auth.getUsuarioId())
                    .map(PilotoResponse::fromDomain)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.status(403).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PilotoResponse> update(@PathVariable("id") Long id,
                                                 @Valid @RequestBody PilotoRequest request) {
        Piloto updated = pilotoUseCase.update(id, request.toDomain(id));
        return ResponseEntity.ok(PilotoResponse.fromDomain(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        pilotoUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}