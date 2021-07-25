package com.accenture.bank.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.EnderecoRepository;


@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
//	@Autowired
//	EnderecoRepository enderecoRepository;
	
	public Cliente saveCliente(Cliente cliente) {
		//enderecoRepository.save(cliente.getEndereco());
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> getAllCliente(){
		
		return clienteRepository.findAll();
		
	}
	public Cliente getById(Long id) {
		return clienteRepository.findById(id).get();
	}
	public Cliente atualizaCliente(long id, Cliente newCliente) {
		newCliente.setIdCliente(id);
		Cliente oldCliente = clienteRepository.findById(id).get();
		if (oldCliente!=null) {
			return clienteRepository.save(newCliente);
		} else
			return null ;
	}
	public void deletaCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}
