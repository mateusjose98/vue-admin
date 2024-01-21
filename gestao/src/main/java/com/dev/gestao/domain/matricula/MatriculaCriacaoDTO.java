package com.dev.gestao.domain.matricula;

import org.springframework.web.multipart.MultipartFile;

public record MatriculaCriacaoDTO(Integer alunoId, Integer turmaId, MultipartFile identificacaoAluno, MultipartFile declaracao){};
