package com.dev.gestao.repos;

import com.dev.gestao.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    boolean existsByCpfIgnoreCase(String cpf);

}