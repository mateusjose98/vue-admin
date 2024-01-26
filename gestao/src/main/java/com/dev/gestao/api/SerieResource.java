package com.dev.gestao.api;

import com.dev.gestao.domain.serie.Serie;
import com.dev.gestao.service.SeriesService;
import com.dev.gestao.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/series")
@RequiredArgsConstructor
public class SerieResource {

    final SeriesService seriesService;
    final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<?> getSeries() {
        List<Serie> body = seriesService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSeries(@PathVariable Integer id) {
        Serie body = seriesService.findById(id);
        return ResponseEntity.ok(body);
    }


}
