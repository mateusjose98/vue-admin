package com.dev.gestao.domain.matricula;

import java.math.BigDecimal;

public record MatriculaConclusaoDTO (Integer idMatricula, Boolean deveCriarAcesso, Integer dia, BigDecimal desconto) {}
