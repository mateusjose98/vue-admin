package com.dev.gestao.repository;

import com.dev.gestao.domain.matricula.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
