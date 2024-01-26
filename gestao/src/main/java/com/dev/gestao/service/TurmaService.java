package com.dev.gestao.service;

import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.repository.TurmaRepository;
import com.dev.gestao.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma findById(Integer id) {
        return turmaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Integer criar(Turma turma) {
        return turmaRepository.save(turma).getId();
    }

    @Transactional
    public void inativar(Integer id) {
        Turma byId = this.findById(id);
        byId.setAtivo(!byId.isAtivo());
    }

    public Integer atualizar(Turma turma) {
        Objects.requireNonNull(turma.getId());
        return turmaRepository.save(turma).getId();
    }
}
