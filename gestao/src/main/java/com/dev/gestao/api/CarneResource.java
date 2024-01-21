package com.dev.gestao.api;

import com.dev.gestao.domain.carne.Parcela;
import com.dev.gestao.service.CarneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/carnes")
@RequiredArgsConstructor
public class CarneResource {

    final CarneService carneService;
    @GetMapping("parcelas/simulacao")
    public ResponseEntity<List<Parcela> > simular(Integer dia, BigDecimal desconto, Integer idMatricula) {
        List<Parcela> parcelas = carneService.simular(dia, desconto, idMatricula);
        return ResponseEntity.ok(parcelas);
    }
}
