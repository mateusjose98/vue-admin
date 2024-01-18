package com.dev.gestao.domain.aluno;

import com.dev.gestao.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Aluno_Turma")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AlunoTurma {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "Aluno_Turma_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;
    private boolean ativo;

    @ManyToOne
    @JoinColumn
    private  Aluno aluno;
    @ManyToOne
    @JoinColumn
    private Turma turma;

    private LocalDateTime data;
}
