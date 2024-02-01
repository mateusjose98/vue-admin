package com.dev.gestao.repository;

import com.dev.gestao.domain.sala.Sala;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

    @Override
    @EntityGraph(attributePaths = {"recursos"})
    Optional<Sala> findById(Integer integer);
}
