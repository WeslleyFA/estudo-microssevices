package com.weslley.mscartoes.application.representation;

import com.weslley.mscartoes.domain.Bandeira;
import com.weslley.mscartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private Bandeira bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda,limiteBasico);
    }
}
