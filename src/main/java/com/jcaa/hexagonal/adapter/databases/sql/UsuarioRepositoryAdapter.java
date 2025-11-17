package com.jcaa.hexagonal.adapter.databases.sql;

import com.jcaa.hexagonal.adapter.databases.sql.mapper.UsuarioEntityMapper;
import com.jcaa.hexagonal.adapter.databases.sql.repository.UsuarioJpaRepository;
import com.jcaa.hexagonal.core.domain.model.Usuario;
import com.jcaa.hexagonal.core.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return UsuarioEntityMapper.toDomain(
                repository.save(UsuarioEntityMapper.toEntity(usuario))
        );
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id).map(UsuarioEntityMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByNombre(String nombre) {
        return repository.findByNombre(nombre).map(UsuarioEntityMapper::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll().stream()
                .map(UsuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}