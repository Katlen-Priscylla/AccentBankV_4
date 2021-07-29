package com.accenture.bank.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bank.entity.Extrato;
import com.accenture.bank.exceptions.ContaNaoEncontradaException;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.ExtratoRepository;

@Service
public class ExtratoService {

	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	ExtratoRepository extratoRepository;
	
	public List<Extrato> getByContaId(Long id)
	{
		if(!contaCorrenteRepository.existsById(id)) {
			throw  new ContaNaoEncontradaException();
		}
		 return contaCorrenteRepository.findById(id).get().getTransacoes();
		 
	}
}
