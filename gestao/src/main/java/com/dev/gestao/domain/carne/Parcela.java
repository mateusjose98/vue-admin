package com.dev.gestao.domain.carne;

import com.dev.gestao.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Parcelas")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Parcela {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "Parcela_sequence",
            sequenceName = "Parcela_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataVencimento;
    @Column(nullable = true)
    private LocalDateTime dataPagamento;
    @Enumerated(EnumType.STRING)
    private StatusParcela statusParcela;
    private String hashPagamento;
    private Integer anoReferencia;
    @ManyToOne
    @JoinColumn
    private Carne carne;

    @OneToOne
    @JoinColumn
    private Usuario usuarioBaixa;


}
