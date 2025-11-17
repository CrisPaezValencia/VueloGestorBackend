package com.jcaa.hexagonal.core.port.out;

import com.jcaa.hexagonal.core.domain.model.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloRepositoryPort {
    Vuelo save(Vuelo vuelo);
    Optional<Vuelo> findById(Long id);
    List<Vuelo> findAll();
    List<Vuelo> findByClienteId(Long clienteId);
    List<Vuelo> findByPilotoId(Long pilotoId);
    void deleteById(Long id);
}