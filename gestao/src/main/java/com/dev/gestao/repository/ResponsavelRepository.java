package com.dev.gestao.repository;

import com.dev.gestao.domain.aluno.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
}
