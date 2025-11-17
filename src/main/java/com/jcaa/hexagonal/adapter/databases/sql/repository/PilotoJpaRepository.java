package com.jcaa.hexagonal.adapter.databases.sql.repository;

import com.jcaa.hexagonal.adapter.databases.sql.entity.PilotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PilotoJpaRepository extends JpaRepository<PilotoEntity, Long> {
    Optional<PilotoEntity> findByUsuarioId(Long usuarioId);
}