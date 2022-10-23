package com.weslley.msclientes.aplication;

import com.weslley.msclientes.domain.Cliente;
import com.weslley.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor //lombok cria um construtor em tempo de execução pras dependencias
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente save(Cliente cliente){
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
