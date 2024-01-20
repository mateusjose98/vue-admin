package com.dev.gestao.domain.serie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Series")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Serie {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "Serie_sequence",
            sequenceName = "Serie_sequence",
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

}
