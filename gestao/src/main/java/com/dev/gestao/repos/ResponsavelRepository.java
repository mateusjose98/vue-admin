package com.dev.gestao.repos;

import com.dev.gestao.domain.aluno.Responsavel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
}
