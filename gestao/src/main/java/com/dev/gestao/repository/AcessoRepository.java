package com.dev.gestao.repository;

import com.dev.gestao.domain.usuario.Acesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer>, JpaSpecificationExecutor<Acesso> {

    Acesso findByDescricao(String descricao);
}