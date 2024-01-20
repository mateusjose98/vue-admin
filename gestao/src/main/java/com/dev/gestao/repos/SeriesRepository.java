package com.dev.gestao.repos;

import com.dev.gestao.domain.serie.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Serie, Integer> {
}
