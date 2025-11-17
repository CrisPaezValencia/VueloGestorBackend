package com.jcaa.hexagonal.adapter.databases.sql;

import com.jcaa.hexagonal.adapter.databases.sql.mapper.VueloEntityMapper;
import com.jcaa.hexagonal.adapter.databases.sql.repository.VueloJpaRepository;
import com.jcaa.hexagonal.core.domain.model.Vuelo;
import com.jcaa.hexagonal.core.port.out.VueloRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VueloRepositoryAdapter implements VueloRepositoryPort {

    private final VueloJpaRepository repository;

    public VueloRepositoryAdapter(VueloJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vuelo save(Vuelo vuelo) {
        return VueloEntityMapper.toDomain(
                repository.save(VueloEntityMapper.toEntity(vuelo))
        );
    }

    @Override
    public Optional<Vuelo> findById(Long id) {
        return repository.findById(id).map(VueloEntityMapper::toDomain);
    }

    @Override
    public List<Vuelo> findAll() {
        return repository.findAll().stream()
                .map(VueloEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Vuelo> findByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(VueloEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Vuelo> findByPilotoId(Long pilotoId) {
        return repository.findByPilotoId(pilotoId).stream()
                .map(VueloEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}