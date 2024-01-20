package com.dev.gestao.service;

import com.dev.gestao.domain.serie.Serie;
import com.dev.gestao.repos.SeriesRepository;
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
}
