package com.dev.gestao.domain.carne;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.dev.gestao.util.BigDecimalUtils.desconto;

@Entity
@Table(name = "Carnes")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Carne {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "Carne_sequence",
            allocationSize = 50,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataEmissao;
    @Column(nullable = false)
    private String usuarioEmissor;

    @OneToMany(mappedBy = "carne")
    private Set<Parcela> parcelas = new HashSet<>();
    private Integer numeroParcelas = 0;

    public void gerarParcelas(int diaVencimento, BigDecimal valorBase, BigDecimal percentualDesconto) {
        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();
        for (int mes = mesAtual; mes <= 12; mes++)
        {
            addParcela(
                    Parcela.builder()
                    .dataVencimento(LocalDate.of(anoAtual, mes, diaVencimento))
                    .anoReferencia(anoAtual)
                    .valor(desconto(valorBase, percentualDesconto))
                    .statusParcela(StatusParcela.EMITIDA).build());
            this.numeroParcelas++;
        }
        this.setDataEmissao(LocalDateTime.now());
    }



    public void addParcela(Parcela parcela) {
        if (this.parcelas.size() <= numeroParcelas) {
            parcela.setCarne(this);
            this.parcelas.add(parcela);
        }

    }

}
