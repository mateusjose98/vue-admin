package com.dev.gestao.service;

import com.dev.gestao.domain.sala.Sala;
import com.dev.gestao.repository.SalaRepository;
import com.dev.gestao.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalaService {

    final SalaRepository salaRepository;

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala getRefById(Integer idSerie) {
        return salaRepository.getReferenceById(idSerie);
    }

    public Sala findById(Integer id) {
        return salaRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
