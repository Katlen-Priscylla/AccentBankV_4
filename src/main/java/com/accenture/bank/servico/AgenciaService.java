package com.accenture.bank.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.exceptions.AgenciaNaoEncontradaException;
import com.accenture.bank.exceptions.ClienteNotFoundException;
import com.accenture.bank.exceptions.FormatoIncorretoCliente;
import com.accenture.bank.exceptions.FormatoInvalidoException;
//import com.accenture.bank.entity.Agencia;
import com.accenture.bank.repository.AgenciaRepository;
//import com.accenture.bank.repository.ContaRepository;
//import com.accenture.bank.repository.EnderecoRepository;

@Service
public class AgenciaService {
	@Autowired
	AgenciaRepository agenciaRepository;
//	@Autowired
//	EnderecoRepository enderecoRepository;
	@Autowired
	AgenciaRepository contaCorrenteRepository;

	public Agencia saveAgencia(Agencia agencia) {
		
		if( agencia.getNome().isBlank()||agencia.getNome().equals(null)||agencia.getNome().isEmpty()
				||agencia.getTelefone().isBlank()||agencia.equals(null)||agencia.getTelefone().isEmpty()){
			
			throw new FormatoInvalidoException();
			
		}
		
				
		// contaCorrenteRepository.saveAll(agencia.getContas());
		return agenciaRepository.save(agencia);
	}

	public List<Agencia> getAllAgencias() {

		return agenciaRepository.findAll();

	}

	public Agencia getById(Long id) {
		
		if(!agenciaRepository.existsById(id)) {
			throw  new AgenciaNaoEncontradaException();
		}
		
		return agenciaRepository.findById(id).get();
	}

	public Agencia atualizaAgencia(long id, Agencia newAgencia) {
		newAgencia.setIdAgencia(id);
		Agencia oldAgencia = agenciaRepository.findById(id).get();
		if (oldAgencia != null) {
			oldAgencia.setNome(newAgencia.getNome());
			oldAgencia.setEndereco(newAgencia.getEndereco());
			newAgencia = oldAgencia;
			return agenciaRepository.save(newAgencia);
		} else
			return null;
	}

	public void deletaAgencia(Long id) {
		if(!agenciaRepository.existsById(id)) {
			throw  new AgenciaNaoEncontradaException();
		}
			
			agenciaRepository.deleteById(id);
}
}
