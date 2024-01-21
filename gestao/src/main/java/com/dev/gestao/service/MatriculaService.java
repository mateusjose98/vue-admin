package com.dev.gestao.service;

import com.dev.gestao.domain.aluno.Aluno;
import com.dev.gestao.domain.aluno.TipoDocumento;
import com.dev.gestao.domain.carne.Carne;
import com.dev.gestao.domain.matricula.Matricula;
import com.dev.gestao.domain.matricula.MatriculaCriacaoDTO;
import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.repos.MatriculaRepository;
import com.dev.gestao.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatriculaService {

    final MatriculaRepository matriculaRepository;
    final AlunoService alunoService;
    final TurmaService turmaService;

    final DocumentoService documentoService;
    @Transactional
    public Integer create(MatriculaCriacaoDTO matriculaDTO) {
        Aluno aluno = alunoService.getRefById(matriculaDTO.alunoId());
        Turma turma = turmaService.getRefById(matriculaDTO.turmaId());
        Matricula matricula = new Matricula();
        matricula.setDataMatricula(LocalDateTime.now());
        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setCarne(new Carne());
        documentoService.create(matriculaDTO.declaracao(), TipoDocumento.DECLARACAO_ESCOLA_ANTIGA, aluno);
        documentoService.create(matriculaDTO.identificacaoAluno(), TipoDocumento.IDENT_ALUNO, aluno);
        Matricula saved = matriculaRepository.save(matricula);
        return saved.getId();
    }

    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    public Matricula findById(Integer idMatricula) {
        return matriculaRepository.findById(idMatricula).orElseThrow(NotFoundException::new);
    }
}
