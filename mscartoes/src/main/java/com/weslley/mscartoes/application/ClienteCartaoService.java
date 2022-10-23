package com.weslley.mscartoes.application;

import com.weslley.mscartoes.domain.ClienteCartao;
import com.weslley.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> cartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
