package com.dev.gestao.api;

import com.dev.gestao.domain.sala.Sala;
import com.dev.gestao.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/salas")
@RequiredArgsConstructor
public class SalaResource {

    final SalaService salasService;

    @GetMapping
    public ResponseEntity<?> getSalas() {
        List<Sala> body = salasService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSala(@PathVariable Integer id) {
        Sala body = salasService.findById(id);
        return ResponseEntity.ok(body);
    }


}
