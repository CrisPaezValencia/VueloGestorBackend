package com.jcaa.hexagonal.core.port.in;

import com.jcaa.hexagonal.core.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioUseCase {
    Usuario create(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombre(String nombre);
    void delete(Long id);
}