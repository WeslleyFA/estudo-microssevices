package com.weslley.msavaliadorcredito.application;

import com.weslley.msavaliadorcredito.domain.model.*;
import com.weslley.msavaliadorcredito.infra.clients.CartaoResourceClient;
import com.weslley.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.weslley.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorService {

    private final ClienteResourceClient clientesClient;
    private final CartaoResourceClient cartoesClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        //consultando o microsserviço de cliente
        ResponseEntity<DadosCliente> dadosCliente = clientesClient.getCliente(cpf);
        ResponseEntity<List<CartaoCliente>> cartoes = cartoesClient.getCartoesByCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosCliente.getBody())
                .cartoes(cartoes.getBody())
                .build();
    }

    public RetornoAvaliacaoCliente realizaAvaliacao(String cpf, Long renda){
        ResponseEntity<DadosCliente> dadosCliente = clientesClient.getCliente(cpf);
        ResponseEntity<List<Cartao>> cartoesRendaAte = cartoesClient.getCartoesRendaAte(renda);

        var cartoesAprovados = cartoesRendaAte.getBody().stream().map(x ->{
            CartaoAprovado cartaoAprovado = new CartaoAprovado(x.getNome(), x.getBandeira(), x.getLimiteBasico());
            return cartaoAprovado;
        }).collect(Collectors.toList());


        return new RetornoAvaliacaoCliente(cartoesAprovados);
    }

    public String solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){
        try{
            emissaoCartaoPublisher.solicitarCartao(dados);
            return "Solicitação concluída";
        }catch (Exception e){
            return "Houve um erro na solicitação";
        }
    }
}
