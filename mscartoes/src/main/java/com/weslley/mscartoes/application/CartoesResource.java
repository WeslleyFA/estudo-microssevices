package com.weslley.mscartoes.application;

import com.weslley.mscartoes.application.representation.CartaoClienteResponse;
import com.weslley.mscartoes.application.representation.CartaoSaveRequest;
import com.weslley.mscartoes.domain.Cartao;
import com.weslley.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CartaoSaveRequest cartaoSaveRequest){
        Cartao cartao = cartaoSaveRequest.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(Long renda){
        List<Cartao> cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
        return  ResponseEntity.ok(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoClienteResponse>> getCartoesByCliente(String cpf){
        List<ClienteCartao> list = clienteCartaoService.cartoesByCpf(cpf);
        List<CartaoClienteResponse> resultList = list.stream().map(CartaoClienteResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
