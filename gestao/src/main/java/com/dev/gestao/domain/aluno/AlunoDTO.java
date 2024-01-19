package com.dev.gestao.domain.aluno;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class AlunoDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @Size(max = 20)
    private String cpf;
    private LocalDate dataNascimento;

    @NotNull
    @Size(max = 100)
    private String email;

    @Size(max = 4)
    private String uf;

    private String foto;

    @Size(max = 255)
    private String telefone;
    private boolean criarAcesso;
    private String matricula;
    private Integer serieId;

    private String nomeResponsavel;
    private String cpfResponsavel;
}
