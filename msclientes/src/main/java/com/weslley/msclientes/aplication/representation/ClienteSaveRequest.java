package com.weslley.msclientes.aplication.representation;

import com.weslley.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(nome, cpf, idade);
    }
}
