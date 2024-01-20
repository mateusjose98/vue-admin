package com.dev.gestao.domain.turma;

import com.dev.gestao.domain.disciplina.Disciplina;
import com.dev.gestao.domain.professor.Professor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Turma_Professor")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class TurmaProfessor {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "TurmaProfessor_sequence",
            sequenceName = "TurmaProfessor_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TurmaProfessor_sequence"
    )
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Turma turma;
    @ManyToOne
    @JoinColumn
    private Professor professor;
    @OneToOne
    private Disciplina disciplina;
}
