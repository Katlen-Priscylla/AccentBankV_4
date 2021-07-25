package com.accenture.bank.servico;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.accenture.bank.entity.Endereco;



@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface CepService {
	@GetMapping("{cep}/json")
    Endereco buscaEnderecoPorCep(@PathVariable("cep") String cep);

}
