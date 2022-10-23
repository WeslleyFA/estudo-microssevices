package com.weslley.mscartoes.application.representation;

import com.weslley.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;


    public static CartaoClienteResponse fromModel(ClienteCartao model){
        return new CartaoClienteResponse(model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite());
    }
}
