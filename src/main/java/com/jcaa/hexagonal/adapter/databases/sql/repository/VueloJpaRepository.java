package com.jcaa.hexagonal.adapter.databases.sql.repository;

import com.jcaa.hexagonal.adapter.databases.sql.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VueloJpaRepository extends JpaRepository<VueloEntity, Long> {
    List<VueloEntity> findByClienteId(Long clienteId);
    List<VueloEntity> findByPilotoId(Long pilotoId);
}