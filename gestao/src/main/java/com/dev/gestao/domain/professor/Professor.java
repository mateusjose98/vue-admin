package com.dev.gestao.domain.professor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
@Entity
@Table(name = "Professores")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Professor {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "Professor_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, length = 20)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String email;

    @Column
    private String telefone;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Professor(ProfessorDTO professorDTO) {
        this.nome = professorDTO.nome();
        this.cpf = professorDTO.cpf();
        this.email = professorDTO.email();
        this.telefone = professorDTO.telefone();
    }
}
