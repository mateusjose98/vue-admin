package com.dev.gestao.api;

import com.amazonaws.Response;
import com.dev.gestao.domain.serie.Serie;
import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.service.SeriesService;
import com.dev.gestao.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
@RequiredArgsConstructor
public class TurmaResource {

    final SeriesService seriesService;
    final TurmaService turmaService;

    @GetMapping("series")
    public ResponseEntity<?> getSeries() {
        List<Serie> body = seriesService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("series/{idSerie}")
    public ResponseEntity<?> getTurmasComVagas(@PathVariable Integer idSerie, @RequestParam(defaultValue = "true") Boolean comVagas) {
        List<Turma> body = turmaService.buscarPorSerieComVagas(idSerie, comVagas);
        return ResponseEntity.ok(body);
    }


}
