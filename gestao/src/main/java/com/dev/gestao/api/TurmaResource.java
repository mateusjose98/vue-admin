package com.dev.gestao.api;

import com.dev.gestao.domain.turma.Turma;
import com.dev.gestao.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
@RequiredArgsConstructor
public class TurmaResource {

    final TurmaService turmaService;



    @GetMapping("series/{idSerie}")
    public ResponseEntity<?> getTurmasComVagas(@PathVariable Integer idSerie, @RequestParam(defaultValue = "true") Boolean comVagas) {
        List<Turma> body = turmaService.buscarPorSerieComVagas(idSerie, comVagas);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<?> getTurmas() {
        List<Turma> body = turmaService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTurma(@PathVariable Integer id){
        Turma body = turmaService.findById(id);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> toggleActive(@PathVariable Integer id){
       turmaService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@RequestBody Turma turma){
        Integer body = turmaService.atualizar(turma);
        return ResponseEntity.ok(body);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody Turma turma){
        Integer body = turmaService.criar(turma);
        return ResponseEntity.ok(body);
    }


}
