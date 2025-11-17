package com.jcaa.hexagonal.adapter.databases.sql;

import com.jcaa.hexagonal.adapter.databases.sql.mapper.PilotoEntityMapper;
import com.jcaa.hexagonal.adapter.databases.sql.repository.PilotoJpaRepository;
import com.jcaa.hexagonal.core.domain.model.Piloto;
import com.jcaa.hexagonal.core.port.out.PilotoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PilotoRepositoryAdapter implements PilotoRepositoryPort {

    private final PilotoJpaRepository repository;

    public PilotoRepositoryAdapter(PilotoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Piloto save(Piloto piloto) {
        return PilotoEntityMapper.toDomain(
                repository.save(PilotoEntityMapper.toEntity(piloto))
        );
    }

    @Override
    public Optional<Piloto> findById(Long id) {
        return repository.findById(id).map(PilotoEntityMapper::toDomain);
    }

    @Override
    public Optional<Piloto> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).map(PilotoEntityMapper::toDomain);
    }

    @Override
    public List<Piloto> findAll() {
        return repository.findAll().stream()
                .map(PilotoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}