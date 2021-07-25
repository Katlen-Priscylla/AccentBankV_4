package com.accenture.bank.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bank.entity.Endereco;
import com.accenture.bank.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repository;

	public Endereco salvarEndereco(Endereco endereco) {

		return repository.save(endereco);
	}
}
