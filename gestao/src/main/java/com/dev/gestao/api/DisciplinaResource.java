package com.dev.gestao.api;

import com.dev.gestao.domain.disciplina.Disciplina;
import com.dev.gestao.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplinas")
@RequiredArgsConstructor
public class DisciplinaResource {

    final DisciplinaService disciplinasService;

    @GetMapping
    public ResponseEntity<?> getSeries() {
        List<Disciplina> body = disciplinasService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSeries(@PathVariable Integer id) {
        Disciplina body = disciplinasService.findById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Disciplina disciplina) {
        Disciplina body = disciplinasService.save(disciplina);
        return ResponseEntity.ok(body);
    }


}
