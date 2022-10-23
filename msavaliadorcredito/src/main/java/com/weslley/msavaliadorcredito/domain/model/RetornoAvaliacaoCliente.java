package com.weslley.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes = new ArrayList<>();
}
