package com.dev.gestao.api;

import com.dev.gestao.domain.professor.FuncionarioBatchItemDTO;
import com.dev.gestao.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/funcionarios")
@RequiredArgsConstructor
@Slf4j
public class FuncionarioResource {

    private final FuncionarioService funcionarioService;

    @PostMapping("batch")
    public ResponseEntity<?> createFuncionarioList(
            @RequestBody List<FuncionarioBatchItemDTO> chunk) {
        log.info("Recebidos para inserir {}", chunk);
        boolean created = funcionarioService.createFuncionarios(chunk);
        return ResponseEntity.ok(created);
    }
}
