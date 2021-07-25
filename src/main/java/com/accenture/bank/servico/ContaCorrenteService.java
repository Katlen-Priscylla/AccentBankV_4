package com.accenture.bank.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {

	@Autowired
	AgenciaRepository agenciaRepository;
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	ClienteRepository clienteRepository;
	
	public ContaCorrente saveConta(ContaCorrente contaCorrente)
	{
//		agenciaRepository.save(contaCorrente.getAgencia());
//		clienteRepository.save(contaCorrente.getCliente());
		
		return contaCorrenteRepository.save(contaCorrente);
		
	}
	
}
