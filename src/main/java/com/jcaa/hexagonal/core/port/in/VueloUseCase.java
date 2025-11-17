package com.jcaa.hexagonal.core.port.in;

import com.jcaa.hexagonal.core.domain.model.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloUseCase {
    Vuelo create(Vuelo vuelo);
    Vuelo update(Long id, Vuelo vuelo);
    List<Vuelo> findAll();
    Optional<Vuelo> findById(Long id);
    List<Vuelo> findByClienteId(Long clienteId);
    List<Vuelo> findByPilotoId(Long pilotoId);
    void delete(Long id);
}