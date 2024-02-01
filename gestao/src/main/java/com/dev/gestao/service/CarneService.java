package com.dev.gestao.service;

import com.dev.gestao.domain.carne.Carne;
import com.dev.gestao.domain.carne.Parcela;
import com.dev.gestao.domain.carne.StatusParcela;
import com.dev.gestao.repository.CarneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.dev.gestao.util.BigDecimalUtils.desconto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarneService {
//    final MatriculaService matriculaService;
    final TurmaService turmaService;
    final CarneRepository carneRepository;

    @Transactional
    public Carne salvar(Carne carne) {
        return carneRepository.save(carne);
    }

//    public List<Parcela> simular(Integer dia, BigDecimal desconto, Integer idMatricula) {
//        Matricula matricula = matriculaService.findById(idMatricula);
//        BigDecimal valorBase = matricula.getTurma().getValorBase();
//        return simularParcelas(dia, valorBase, desconto);
//    }

    public List<Parcela> simularParcelas(int diaVencimento, BigDecimal valorBase, BigDecimal percentualDesconto) {
        List<Parcela> result = new ArrayList<>();
        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();
        for (int mes = mesAtual; mes <= 12; mes++)
        {
            result.add(
                    Parcela.builder()
                            .dataVencimento(LocalDate.of(anoAtual, mes, diaVencimento))
                            .anoReferencia(anoAtual)
                            .valor(desconto(valorBase, percentualDesconto))
                            .statusParcela(StatusParcela.EMITIDA).build());
        }

        return result;
    }
}
