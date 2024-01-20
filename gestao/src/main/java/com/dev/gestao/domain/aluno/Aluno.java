package com.dev.gestao.domain.aluno;

import com.dev.gestao.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "Alunos")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Aluno {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "Aluno_sequence",
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

    @Column(length = 4)
    private String uf;

    @Column(columnDefinition = "text")
    private String foto;

    @Column
    private String matricula;

    @Column
    private String telefone;
    private LocalDate dataNascimento;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Usuario usuario;



    public int idade() {
        final Period periodo = Period.between(this.dataNascimento, LocalDate.now());
        return periodo.getYears();
    }

    public void gerarMatricula(Integer id) {
        this.setMatricula(String.valueOf(LocalDate.now().getYear()) +  id);
    }
}
