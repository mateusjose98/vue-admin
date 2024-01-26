package com.dev.gestao.repository;

import com.dev.gestao.domain.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    boolean existsByCpfIgnoreCase(String cpf);

}
