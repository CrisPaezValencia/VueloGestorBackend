package com.jcaa.hexagonal.core.port.out;

import com.jcaa.hexagonal.core.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByUsuarioId(Long usuarioId);
    List<Cliente> findAll();
    void deleteById(Long id);
}