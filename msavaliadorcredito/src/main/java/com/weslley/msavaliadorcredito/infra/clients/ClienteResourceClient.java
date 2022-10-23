package com.weslley.msavaliadorcredito.infra.clients;

import com.weslley.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//usa o open feign do spring cloud pra fazer comunicações sincronas entre os microsserviços
@FeignClient(value = "msclientes", path = "clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> getCliente(@RequestParam("cpf") String cpf);
}
