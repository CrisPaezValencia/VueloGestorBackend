package com.jcaa.hexagonal.core.port.in;

import com.jcaa.hexagonal.core.domain.model.Piloto;

import java.util.List;
import java.util.Optional;

public interface PilotoUseCase {
    Piloto create(Piloto piloto);
    Piloto update(Long id, Piloto piloto);
    List<Piloto> findAll();
    Optional<Piloto> findById(Long id);
    Optional<Piloto> findByUsuarioId(Long usuarioId);
    void delete(Long id);
}