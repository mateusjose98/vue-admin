package com.dev.gestao.domain.aluno;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Documentos")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "Documentos_sequence",
            sequenceName = "Documentos_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Documentos_sequence"
    )
    private Integer id;
    private String link;
    private String nome;
    private String extensao;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @ManyToOne
    @JoinColumn
    private Aluno aluno;
}
