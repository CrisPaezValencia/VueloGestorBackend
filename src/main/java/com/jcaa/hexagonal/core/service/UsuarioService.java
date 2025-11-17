package com.jcaa.hexagonal.core.service;

import com.jcaa.hexagonal.core.domain.model.Rol;
import com.jcaa.hexagonal.core.domain.model.Usuario;
import com.jcaa.hexagonal.core.port.in.UsuarioUseCase;
import com.jcaa.hexagonal.core.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort repository;

    public UsuarioService(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Usuario create(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Usuario current = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
        Usuario toSave = new Usuario(
                current.id(),
                usuario.nombre(),
                usuario.clave(),
                usuario.rol() != null ? usuario.rol() : current.rol()
        );
        return repository.save(toSave);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Usuario> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}