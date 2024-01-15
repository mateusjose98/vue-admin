package com.dev.gestao.rest;

import com.dev.gestao.domain.aluno.AlunoDTO;
import com.dev.gestao.service.AlunoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoResource {

    private final AlunoService alunoService;

    public AlunoResource(final AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAllAlunos() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(alunoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createAluno(@RequestBody @Valid final AlunoDTO alunoDTO) {
        final Integer createdId = alunoService.create(alunoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PostMapping("batch")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Void> createAlunoBatch(@RequestBody @Valid final List<AlunoDTO> lista) {
       alunoService.createInBatch(lista);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping(value = "upsert-foto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Map<String, String>> upsertFoto(@PathVariable final Integer id, @RequestParam("file") MultipartFile file) {
        final String foto = alunoService.upsertFoto(id, file);
        return new ResponseEntity<>(Map.of("link", foto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateAluno(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final AlunoDTO alunoDTO) {
        alunoService.update(id, alunoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAluno(@PathVariable(name = "id") final Integer id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
