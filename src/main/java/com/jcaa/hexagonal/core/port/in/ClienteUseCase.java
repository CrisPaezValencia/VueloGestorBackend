package com.jcaa.hexagonal.core.port.in;

import com.jcaa.hexagonal.core.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteUseCase {
    Cliente create(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByUsuarioId(Long usuarioId);
    void delete(Long id);
}