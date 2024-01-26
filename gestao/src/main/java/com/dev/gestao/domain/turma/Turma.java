package com.dev.gestao.domain.turma;

import com.dev.gestao.domain.sala.Sala;
import com.dev.gestao.domain.serie.Serie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Table(name = "Turmas")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Turma {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "Turma_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;
    private int ano;
    private String descricao;
    private Integer vagasDisponiveis;
    private BigDecimal valorBase;

    @OneToOne
    @JoinColumn
    private Sala sala;

    @OneToOne
    @JoinColumn
    private Serie serie;
    private boolean ativo;



}
