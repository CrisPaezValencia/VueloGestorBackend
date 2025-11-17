package com.jcaa.hexagonal.core.service;

import com.jcaa.hexagonal.core.domain.model.Cliente;
import com.jcaa.hexagonal.core.port.in.ClienteUseCase;
import com.jcaa.hexagonal.core.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort repository;

    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Cliente create(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        Cliente current = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
        Cliente updated = new Cliente(
                current.id(),
                cliente.usuarioId(),
                cliente.email(),
                cliente.telefono()
        );
        return repository.save(updated);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Cliente> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}