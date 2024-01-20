package com.dev.gestao.domain.carne;

import com.dev.gestao.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn
    private Usuario usuarioEmissor;

    @OneToMany(mappedBy = "carne")
    private Set<Parcela> parcelas = new HashSet<>();
    private Integer numeroParcelas;

    public void gerarParcelas(int diaVencimento) {
        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();
        for (int mes = mesAtual; mes <= 12; mes++)
        {
            addParcela(
                    Parcela.builder()
                    .dataVencimento(LocalDate.of(anoAtual, mes, diaVencimento))
                    .anoReferencia(anoAtual)
                    .statusParcela(StatusParcela.EMITIDA).build());
            this.numeroParcelas++;
        }
    }

    public void addParcela(Parcela parcela) {
        if (this.parcelas.size() <= numeroParcelas) {
            parcela.setCarne(this);
            this.parcelas.add(parcela);
        }

    }

}
