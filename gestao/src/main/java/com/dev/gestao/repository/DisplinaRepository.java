package com.dev.gestao.repository;

import com.dev.gestao.domain.disciplina.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisplinaRepository extends JpaRepository<Disciplina, Integer> {
}
