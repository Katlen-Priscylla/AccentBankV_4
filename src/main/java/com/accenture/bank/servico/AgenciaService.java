package com.accenture.bank.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;
//import com.accenture.bank.repository.ContaRepository;
import com.accenture.bank.repository.EnderecoRepository;

@Service
public class AgenciaService {
	@Autowired
	AgenciaRepository agenciaRepository;
//	@Autowired
//	EnderecoRepository enderecoRepository;
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
	
	public Agencia saveAgencia(Agencia agencia) {
		contaCorrenteRepository.saveAll(agencia.getContas());
		return agenciaRepository.save(agencia);
	}
}
