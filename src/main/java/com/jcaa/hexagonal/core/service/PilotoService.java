package com.jcaa.hexagonal.core.service;

import com.jcaa.hexagonal.core.domain.model.Piloto;
import com.jcaa.hexagonal.core.port.in.PilotoUseCase;
import com.jcaa.hexagonal.core.port.out.PilotoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotoService implements PilotoUseCase {

    private final PilotoRepositoryPort repository;

    public PilotoService(PilotoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Piloto create(Piloto piloto) {
        return repository.save(piloto);
    }

    @Override
    public Piloto update(Long id, Piloto piloto) {
        Piloto current = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Piloto no encontrado: " + id));
        Piloto updated = new Piloto(
                current.id(),
                piloto.usuarioId(),
                piloto.licencia()
        );
        return repository.save(updated);
    }

    @Override
    public List<Piloto> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Piloto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Piloto> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}