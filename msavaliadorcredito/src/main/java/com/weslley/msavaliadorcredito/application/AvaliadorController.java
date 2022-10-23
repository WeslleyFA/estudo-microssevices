package com.weslley.msavaliadorcredito.application;

import com.weslley.msavaliadorcredito.domain.model.DadosAvaliacao;
import com.weslley.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import com.weslley.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import com.weslley.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/avaliador")
@RequiredArgsConstructor
public class AvaliadorController {

    private final AvaliadorService avaliadorService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "/situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(String cpf){
        SituacaoCliente situacao = avaliadorService.obterSituacaoCliente(cpf);
        return ResponseEntity.ok(situacao);
    }

    @PostMapping
    public ResponseEntity realizaAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao){
        RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorService.realizaAvaliacao(dadosAvaliacao.getCpf(), dadosAvaliacao.getRenda());
        return ResponseEntity.ok(retornoAvaliacaoCliente);

    }

    @PostMapping(value = "solicitar-cartao")
    public String solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
        String resposta = avaliadorService.solicitarEmissaoCartao(dados);
        return resposta;
    }


}
