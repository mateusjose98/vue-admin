package com.dev.gestao.service;

import com.dev.gestao.domain.aluno.Aluno;
import com.dev.gestao.domain.aluno.TipoDocumento;
import com.dev.gestao.domain.carne.Carne;
import com.dev.gestao.domain.matricula.Matricula;
import com.dev.gestao.domain.matricula.MatriculaConclusaoDTO;
import com.dev.gestao.domain.matricula.MatriculaCriacaoDTO;
import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.domain.usuario.Usuario;
import com.dev.gestao.repository.MatriculaRepository;
import com.dev.gestao.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatriculaService {

    final MatriculaRepository matriculaRepository;
    final AlunoService alunoService;
    final TurmaService turmaService;
    final UsuarioService usuarioService;
    final DocumentoService documentoService;
    final NotificacaoService notificacaoService;
    final CarneService carneService;
    
    @Transactional
    public Integer create(MatriculaCriacaoDTO matriculaDTO) {
        Aluno aluno = alunoService.getRefById(matriculaDTO.alunoId());
        Turma turma = turmaService.getRefById(matriculaDTO.turmaId());
        Matricula matricula = new Matricula();
        matricula.setDataMatricula(LocalDateTime.now());
        matricula.setAluno(aluno);
        matricula.setTurma(turma);


        if(Objects.nonNull(matriculaDTO.declaracao())) {
            documentoService.create(matriculaDTO.declaracao(), TipoDocumento.DECLARACAO_ESCOLA_ANTIGA, aluno);
        }
        if(Objects.nonNull(matriculaDTO.identificacaoAluno())) {
            documentoService.create(matriculaDTO.identificacaoAluno(), TipoDocumento.IDENT_ALUNO, aluno);
        }

        Matricula saved = matriculaRepository.save(matricula);
        return saved.getId();
    }

    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    public Matricula findById(Integer idMatricula) {
        return matriculaRepository.findById(idMatricula).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Matricula concluir(MatriculaConclusaoDTO dto) {
        Matricula matricula = this.findById(dto.idMatricula());
        Aluno aluno = matricula.getAluno();
        Turma turma = matricula.getTurma();
        if(dto.deveCriarAcesso()) {
            Usuario usuario = usuarioService.criaraPartirDasCredenciais(aluno.getEmail(), aluno.getCpf(), "ROLE_ALUNO");
            aluno.setUsuario(usuario);
        }
        var carne = new Carne();
        carne.gerarParcelas(dto.dia(), turma.getValorBase(), dto.desconto());
        carne.setUsuarioEmissor("secretaria@teste.com" /*usuarioService.getCurrentUsername().getLogin()*/);
        carne = carneService.salvar(carne);
        matricula.setCarne(carne);
        return matricula;
    }


}
