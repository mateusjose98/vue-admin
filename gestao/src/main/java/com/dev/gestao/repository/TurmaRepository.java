package com.dev.gestao.repository;

import com.dev.gestao.domain.serie.Serie;
import com.dev.gestao.domain.turma.Turma;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    @EntityGraph(attributePaths = {"sala", "serie", "sala.recursos"})
    List<Turma> findBySerie(Serie serie);
}
