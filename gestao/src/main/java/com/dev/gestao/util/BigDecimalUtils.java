package com.dev.gestao.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {

    public static BigDecimal desconto(BigDecimal valor, BigDecimal percentual) {
        BigDecimal cem = BigDecimal.valueOf(100);
        BigDecimal multiplicador = cem.subtract(percentual);
        return valor.multiply(multiplicador.divide(cem,2, RoundingMode.HALF_EVEN));
    }
}
