package com.dev.gestao.service;

import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.repos.TurmaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TurmaService {

    final TurmaRepository turmaRepository;
    final SeriesService seriesService;
    public Turma getRefById(Integer integer) {

        return turmaRepository.getReferenceById(integer);

    }

    public List<Turma> buscarPorSerieComVagas(  Integer idSerie,
                                                Boolean restricaoComVagas) {
        List<Turma> turmas =  turmaRepository.findBySerie(seriesService.getRefById(idSerie));
        if (!restricaoComVagas) return turmas;
        return turmas.stream().filter(turma -> turma.getVagasDisponiveis() > 0).toList();
    }
}
