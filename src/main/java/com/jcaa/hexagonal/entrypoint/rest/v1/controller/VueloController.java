package com.jcaa.hexagonal.entrypoint.rest.v1.controller;

import com.jcaa.hexagonal.core.domain.model.Vuelo;
import com.jcaa.hexagonal.core.port.in.VueloUseCase;
import com.jcaa.hexagonal.entrypoint.rest.v1.common.AuthenticatedUser;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.VueloRequest;
import com.jcaa.hexagonal.entrypoint.rest.v1.dto.VueloResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelos")
public class VueloController {

    private final VueloUseCase vueloUseCase;

    public VueloController(VueloUseCase vueloUseCase) {
        this.vueloUseCase = vueloUseCase;
    }

    @PreAuthorize("hasAnyRole('ADMIN','PILOTO','CLIENTE')")
    @GetMapping
    public ResponseEntity<List<VueloResponse>> findAll() {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        List<Vuelo> vuelos;
        switch (auth.getRol()) {
            case ADMIN -> vuelos = vueloUseCase.findAll();
            case PILOTO -> vuelos = vueloUseCase.findByPilotoId(auth.getPilotoId());
            case CLIENTE -> vuelos = vueloUseCase.findByClienteId(auth.getClienteId());
            default -> vuelos = List.of();
        }
        return ResponseEntity.ok(vuelos.stream().map(VueloResponse::fromDomain).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<VueloResponse> create(@Valid @RequestBody VueloRequest request) {
        Vuelo created = vueloUseCase.create(request.toDomain());
        return ResponseEntity.created(URI.create("/api/v1/vuelos/" + created.id()))
                .body(VueloResponse.fromDomain(created));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<VueloResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody VueloRequest request) {
        Vuelo updated = vueloUseCase.update(id, request.toDomainWithId(id));
        return ResponseEntity.ok(VueloResponse.fromDomain(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vueloUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PILOTO','CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<VueloResponse> findById(@PathVariable Long id) {
        return vueloUseCase.findById(id)
                .map(VueloResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}