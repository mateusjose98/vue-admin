package com.dev.gestao.repository;

import com.dev.gestao.domain.sala.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
