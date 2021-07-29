package com.accenture.bank.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.exceptions.ClienteNotFoundException;
import com.accenture.bank.exceptions.FormatoIncorretoCliente;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.EnderecoRepository;


@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
//	@Autowired
//	EnderecoRepository enderecoRepository;
	
	public Cliente saveCliente(Cliente cliente) {
	if (cliente.getNome().isEmpty() || cliente.getNome().equals(null)||cliente.getNome().isBlank()
			||cliente.getTelefone()
			.isEmpty()||cliente.getTelefone().equals(null)||cliente.getTelefone().isBlank()) {
		throw new FormatoIncorretoCliente();
	}
		return clienteRepository.save(cliente);
	
	}
	public List<Cliente> getAllCliente(){
		
		return clienteRepository.findAll();
		
	}
	public Cliente getById(Long id) {
		
		if(!clienteRepository.existsById(id)) {
			throw  new ClienteNotFoundException();
		}
		return clienteRepository.findById(id).get();
	}
	
	
	public Cliente atualizaCliente(long id, Cliente newCliente) {
		
		newCliente.setIdCliente(id);
		
		Cliente oldCliente = clienteRepository.findById(id).get();
		
		if (oldCliente==null ||!clienteRepository.existsById(id)) {
			throw  new ClienteNotFoundException();
		} 
		return clienteRepository.save(newCliente);
	}
	
	
	public void deletaCliente(Long id) {
		
		
		if(!clienteRepository.existsById(id)) {
			throw  new ClienteNotFoundException();
		}
			
			clienteRepository.deleteById(id);
		
	}
		
		
		
		//clienteRepository.deleteById(id);
	}

