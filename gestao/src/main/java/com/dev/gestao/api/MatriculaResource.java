package com.dev.gestao.api;


import com.dev.gestao.domain.matricula.Matricula;
import com.dev.gestao.domain.matricula.MatriculaConclusaoDTO;
import com.dev.gestao.domain.matricula.MatriculaCriacaoDTO;
import com.dev.gestao.service.MatriculaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/matriculas")
@RequiredArgsConstructor
public class MatriculaResource {

    final MatriculaService matriculaService;


    @GetMapping
    public ResponseEntity<List<Matricula>> getAll() {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> create(MatriculaCriacaoDTO matriculaDTO) {
        final Integer createdId = matriculaService.create(matriculaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("conclusao")
    public ResponseEntity<Matricula> concluirMatricula(@RequestBody MatriculaConclusaoDTO dto) {
        final Matricula updatedId = matriculaService.concluir(dto);
        return ResponseEntity.ok(updatedId);
    }




}
