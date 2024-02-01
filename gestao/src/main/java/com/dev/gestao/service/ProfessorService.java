package com.dev.gestao.service;

import com.dev.gestao.domain.professor.Professor;
import com.dev.gestao.domain.professor.ProfessorDTO;
import com.dev.gestao.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessorService {

    final ProfessorRepository professorRepository;
    final UsuarioService usuarioService;
    public List<Professor> findAll() {
        var result = professorRepository.findAll(Sort.by("id").descending());
        return result;
    }

    @Transactional
    public Integer createProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor(professorDTO);
        Professor fromDB = professorRepository.save(professor);
        if(professorDTO.criarAcesso()){
            usuarioService.criaraPartirDasCredenciais(professorDTO.email(), professorDTO.cpf(), "ROLE_PROFESSOR");
        }
        return fromDB.getId();
    }
}
