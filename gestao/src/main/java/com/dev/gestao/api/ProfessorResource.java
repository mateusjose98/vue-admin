package com.dev.gestao.api;

import com.dev.gestao.domain.professor.Professor;
import com.dev.gestao.domain.professor.ProfessorDTO;
import com.dev.gestao.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professores")
@RequiredArgsConstructor
public class ProfessorResource {
    final ProfessorService professorService;
    @GetMapping
    public ResponseEntity<?> getProfessores() {
        List<Professor> professores = professorService.findAll();
        return ResponseEntity.ok(professores);
    }

    @PostMapping
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorDTO professorDTO) {
       Integer id = professorService.createProfessor(professorDTO);
       return ResponseEntity.ok(id);
    }



}
