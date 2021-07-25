package com.accenture.bank.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.servico.ContaCorrenteService;
import com.accenture.bank.servico.ContaCorrenteService;

@RestController
@RequestMapping("contas/")
public class ContaCorrenteController {
	@Autowired
	ContaCorrenteService contaCorrenteService;
	
	@PostMapping 
	public ContaCorrente save(@RequestBody ContaCorrente contaCorrente) {
		return contaCorrenteService.saveConta(contaCorrente);
	}
	
	@GetMapping
	public List<ContaCorrente> findAll (){
		return contaCorrenteService.getAllContaCorrente();
	}
	@GetMapping("{id}")
	public ContaCorrente findId(@PathVariable Long id) {
		return contaCorrenteService.getByIdContaCorrente(id);
	}
	@PutMapping("{id}")
	public ContaCorrente atualizaContaCorrente(@PathVariable Long id, @RequestBody ContaCorrente contaCorrente ) {
		return contaCorrenteService.atualizaContaCorrente(id, contaCorrente);
	}
	@DeleteMapping("{id}")
	public void deletaById(@PathVariable Long id) {
		contaCorrenteService.deletaContaCorrente(id);
	}
	
	@PutMapping("{id}/sacar")
	public ContaCorrente saque(@PathVariable Long id, @RequestBody double valor) {
	return	contaCorrenteService.saque(id, valor);
	}
	@PutMapping("{id}/depositar")
	public ContaCorrente deposita(@PathVariable Long id, @RequestBody double valor) {
	return	contaCorrenteService.deposita(id, valor);
	}
	
	@PutMapping("{idOrigem}/transferir/{idDestino}")
	public Map<String,ContaCorrente> transfere(@PathVariable Long idOrigem,@PathVariable Long idDestino, @RequestBody double valor) {
	return	contaCorrenteService.transfere(idOrigem, idDestino, valor);
	}
}
