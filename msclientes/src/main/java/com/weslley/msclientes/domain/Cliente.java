package com.weslley.msclientes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data //anotação do lombok, gera os get e set sem precisar implementar
@NoArgsConstructor //o lombok faz um construtor sem parametros
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente(String nome, String cpf, Integer idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
}
