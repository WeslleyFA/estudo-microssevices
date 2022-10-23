package com.weslley.mscartoes.infa.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weslley.mscartoes.domain.Cartao;
import com.weslley.mscartoes.domain.ClienteCartao;
import com.weslley.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import com.weslley.mscartoes.infra.repository.CartaoRepository;
import com.weslley.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;


    //olha o nome da fila que foi declarado no application yml
    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload){
        System.out.println(payload);
        try {
            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao(dados.getCpf(), cartao, dados.getLimiteLiberado());
            clienteCartaoRepository.save(clienteCartao);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
