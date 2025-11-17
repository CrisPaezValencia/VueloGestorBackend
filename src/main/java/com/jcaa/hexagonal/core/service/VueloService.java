package com.jcaa.hexagonal.core.service;

import com.jcaa.hexagonal.core.domain.model.Vuelo;
import com.jcaa.hexagonal.core.port.in.VueloUseCase;
import com.jcaa.hexagonal.core.port.out.VueloRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloService implements VueloUseCase {

    private final VueloRepositoryPort repository;

    public VueloService(VueloRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Vuelo create(Vuelo vuelo) {
        return repository.save(vuelo);
    }

    @Override
    public Vuelo update(Long id, Vuelo vuelo) {
        repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado: " + id));
        return repository.save(vuelo);
    }

    @Override
    public List<Vuelo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Vuelo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Vuelo> findByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    @Override
    public List<Vuelo> findByPilotoId(Long pilotoId) {
        return repository.findByPilotoId(pilotoId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}