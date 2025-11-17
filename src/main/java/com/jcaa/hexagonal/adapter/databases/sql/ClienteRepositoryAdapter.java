package com.jcaa.hexagonal.adapter.databases.sql;

import com.jcaa.hexagonal.adapter.databases.sql.mapper.ClienteEntityMapper;
import com.jcaa.hexagonal.adapter.databases.sql.repository.ClienteJpaRepository;
import com.jcaa.hexagonal.core.domain.model.Cliente;
import com.jcaa.hexagonal.core.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository repository;

    public ClienteRepositoryAdapter(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return ClienteEntityMapper.toDomain(
                repository.save(ClienteEntityMapper.toEntity(cliente))
        );
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id).map(ClienteEntityMapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).map(ClienteEntityMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll().stream()
                .map(ClienteEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}