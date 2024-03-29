package com.dev.gestao.domain.sala;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "Salas")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Sala {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "Sala_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private String nome;
    private Integer capacidade;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> recursos;
}
