package com.accenture.bank.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {

//	@Autowired
//	AgenciaRepository agenciaRepository;
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
//	@Autowired
//	ClienteRepository clienteRepository;

	public ContaCorrente saveConta(ContaCorrente contaCorrente) {
//		agenciaRepository.save(contaCorrente.getAgencia());
//		clienteRepository.save(contaCorrente.getCliente());

		return contaCorrenteRepository.save(contaCorrente);

	}

	public List<ContaCorrente> getAllContaCorrente() {

		return contaCorrenteRepository.findAll();

	}

	public ContaCorrente getByIdContaCorrente(Long id) {
		return contaCorrenteRepository.findById(id).get();
	}

	public ContaCorrente atualizaContaCorrente(long id, ContaCorrente newContaCorrente) {
		newContaCorrente.setIdContaCorrente(id);
		ContaCorrente oldContaCorrente = contaCorrenteRepository.findById(id).get();
		if (oldContaCorrente != null) {
			
			oldContaCorrente.setValor(newContaCorrente.getValor());
			newContaCorrente = oldContaCorrente;
			return contaCorrenteRepository.save(newContaCorrente);
		} else
			return null;
	}

	public void deletaContaCorrente(Long id) {
		contaCorrenteRepository.deleteById(id);
	}
}
