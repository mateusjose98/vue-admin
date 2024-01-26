package com.dev.gestao.service;

import com.dev.gestao.domain.disciplina.Disciplina;
import com.dev.gestao.repository.DisplinaRepository;
import com.dev.gestao.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DisciplinaService {

    final DisplinaRepository displinaRepository;

    public List<Disciplina> findAll() {
        return displinaRepository.findAll();
    }

    public Disciplina getRefById(Integer idSerie) {
        return displinaRepository.getReferenceById(idSerie);
    }

    public Disciplina findById(Integer id) {
        return displinaRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
