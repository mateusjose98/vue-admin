package com.dev.gestao.repository;

import com.dev.gestao.domain.aluno.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}
