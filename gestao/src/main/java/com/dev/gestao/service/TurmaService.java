package com.dev.gestao.service;

import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.repos.TurmaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TurmaService {

    final TurmaRepository turmaRepository;
    public Turma getRefById(Integer integer) {

        return turmaRepository.getReferenceById(integer);

    }
}
