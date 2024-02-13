package com.dev.gestao.service;

import com.dev.gestao.domain.professor.FuncionarioBatchItemDTO;
import com.dev.gestao.domain.professor.FuncionarioType;
import com.dev.gestao.domain.professor.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FuncionarioService {

    private final ProfessorService professorService;


    @Transactional
    public boolean createFuncionarios(List<FuncionarioBatchItemDTO> chunk) {

        Set<Professor> professores = chunk
                .stream()
                .filter(c -> FuncionarioType.PROFESSOR.equals(c.tipo()))
                .map(dtoProf -> new Professor(dtoProf.nome(), dtoProf.cpf(), dtoProf.email(), null))
                .collect(Collectors.toSet());

        return professorService.saveAll(new ArrayList<>(professores));


    }
}
