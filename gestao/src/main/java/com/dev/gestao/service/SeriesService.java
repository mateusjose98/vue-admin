package com.dev.gestao.service;

import com.dev.gestao.domain.serie.Serie;
import com.dev.gestao.repository.SeriesRepository;
import com.dev.gestao.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeriesService {

    final SeriesRepository seriesRepository;
    public List<Serie> findAll() {
        return seriesRepository.findAll();
    }

    public Serie getRefById(Integer idSerie) {
        return seriesRepository.getReferenceById(idSerie);
    }

    public Serie findById(Integer id) {
        return seriesRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
