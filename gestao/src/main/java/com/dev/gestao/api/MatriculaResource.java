package com.dev.gestao.api;


import com.dev.gestao.domain.matricula.Matricula;
import com.dev.gestao.domain.matricula.MatriculaCriacaoDTO;
import com.dev.gestao.service.MatriculaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/matriculas")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatriculaResource {

    final MatriculaService matriculaService;


    @GetMapping
    public ResponseEntity<List<Matricula>> getAll() {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> create(@RequestBody @Valid final MatriculaCriacaoDTO matriculaDTO) {
        final Integer createdId = matriculaService.create(matriculaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }


}
