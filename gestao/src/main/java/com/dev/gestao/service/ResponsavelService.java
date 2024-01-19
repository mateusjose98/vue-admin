package com.dev.gestao.service;

import com.dev.gestao.domain.aluno.Aluno;
import com.dev.gestao.domain.aluno.Responsavel;
import com.dev.gestao.repos.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    @Transactional
    public void criar(String cpfResponsavel, String nome, Aluno salvo) {
        Responsavel responsavel =
                responsavelRepository.save(Responsavel.builder().nomeResponsavel(nome).cpfResponsavel(cpfResponsavel).build());
        responsavel.setAluno(salvo);
    }
}
