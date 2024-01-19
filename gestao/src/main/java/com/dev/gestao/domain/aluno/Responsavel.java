package com.dev.gestao.domain.aluno;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Responsaveis")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@SuperBuilder
public class Responsavel {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "Responsavel_sequence",
            sequenceName = "Responsavel_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Responsavel_sequence"
    )
    private Integer id;
    private String nomeResponsavel;

    @Column(length = 20)
    private String cpfResponsavel;
    @Column(length = 20)
    private String telefoneResponsavel;
    @ManyToOne
    @JoinColumn
    private Aluno aluno;
}
