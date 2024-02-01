package com.dev.gestao.domain.matricula;

import com.dev.gestao.domain.aluno.Aluno;
import com.dev.gestao.domain.carne.Carne;
import com.dev.gestao.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Matriculas")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Matricula {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "Matricula_sequence",
            sequenceName = "Matricula_sequence",
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
    private Aluno aluno;
    @ManyToOne
    @JoinColumn
    private Turma turma;
    private LocalDateTime dataMatricula;
    @OneToOne
    @JoinColumn
    private Carne carne;

}
