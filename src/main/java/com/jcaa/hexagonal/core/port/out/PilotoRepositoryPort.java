package com.jcaa.hexagonal.core.port.out;

import com.jcaa.hexagonal.core.domain.model.Piloto;

import java.util.List;
import java.util.Optional;

public interface PilotoRepositoryPort {
    Piloto save(Piloto piloto);
    Optional<Piloto> findById(Long id);
    Optional<Piloto> findByUsuarioId(Long usuarioId);
    List<Piloto> findAll();
    void deleteById(Long id);
}