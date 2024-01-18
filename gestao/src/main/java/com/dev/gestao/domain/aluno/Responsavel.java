package com.dev.gestao.domain.aluno;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Responsavel {

    private String nomeResponsavel;

    @Column(length = 20)
    private String cpfResponsavel;
}
