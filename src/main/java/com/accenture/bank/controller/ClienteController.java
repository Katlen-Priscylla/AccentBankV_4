package com.accenture.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bank.entity.Cliente;
import com.accenture.bank.servico.ClienteService;

@RestController
@RequestMapping("/clientes/")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@PostMapping
	public Cliente save(@RequestBody Cliente cliente) {
		return clienteService.saveCliente(cliente);
	}
	
	@GetMapping
	public List<Cliente> findAll (){
		return clienteService.getAllCliente();
	}
	@GetMapping("{id}")
	public Cliente findId(@PathVariable Long id) {
		return clienteService.getById(id);
	}
	@PutMapping("{id}")
	public Cliente atualizaCliente(@PathVariable Long id, @RequestBody Cliente cliente ) {
		return clienteService.atualizaCliente(id, cliente);
	}
	@DeleteMapping("{id}")
	public void deletaById(@PathVariable Long id) {
		clienteService.deletaCliente(id);
	}
	
}
