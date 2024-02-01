package com.dev.gestao.repository;

import com.dev.gestao.domain.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
