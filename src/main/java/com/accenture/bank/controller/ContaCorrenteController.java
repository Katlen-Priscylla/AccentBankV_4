package com.accenture.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Extrato;
import com.accenture.bank.entity.Transacao;
import com.accenture.bank.servico.ContaCorrenteService;
import com.accenture.bank.servico.ExtratoService;

@RestController
@RequestMapping("contas/")
public class ContaCorrenteController {
	@Autowired
	ContaCorrenteService contaCorrenteService;
	@Autowired
	ExtratoService extratoService;

	@PostMapping
	public ContaCorrente save(@RequestBody ContaCorrente contaCorrente) {
		return contaCorrenteService.saveConta(contaCorrente);
	}

	@GetMapping
	public List<ContaCorrente> findAll() {
		return contaCorrenteService.getAllContaCorrente();
	}

	@GetMapping("{id}")
	public ContaCorrente findId(@PathVariable Long id) {
		return contaCorrenteService.getByIdContaCorrente(id);
	}

	@PutMapping("{id}")
	public ContaCorrente atualizaContaCorrente(@PathVariable Long id, @RequestBody ContaCorrente contaCorrente) {
		return contaCorrenteService.atualizaContaCorrente(id, contaCorrente);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletaById(@PathVariable Long id) {
		contaCorrenteService.deletaContaCorrente(id);
	}

	@PutMapping("{id}/sacar")
	public ContaCorrente saque(@PathVariable Long id, @RequestBody double valor) {
		return contaCorrenteService.saque(id, valor,Transacao.SAQUE);
	}

	@PutMapping("{id}/depositar")
	public ContaCorrente deposita(@PathVariable Long id, @RequestBody double valor) {
		return contaCorrenteService.deposita(id, valor, Transacao.DEPOSITO);
	}

	@PutMapping("{idOrigem}/transferir/{idDestino}")
	public Map<String, ContaCorrente> transfere(@PathVariable Long idOrigem, @PathVariable Long idDestino,
			@RequestBody double valor) {
		return contaCorrenteService.transfere(idOrigem, idDestino, valor);
	}

	@GetMapping("{id}/extrato")
	public List<Extrato> findExtrato(@PathVariable Long id) {
		return extratoService.getByContaId(id);
	}
	
	@GetMapping("{id}/mostrarextrato")
	public ModelAndView listar(@PathVariable Long id) {
		List<Extrato> lista = extratoService.getByContaId(id);
		ContaCorrente conta = contaCorrenteService.getByIdContaCorrente(id);
		
		ModelAndView modelAndView = new ModelAndView("mostrarextrato");		
		modelAndView.addObject("mostrarextrato", lista);
		modelAndView.addObject("conta", conta);
		 
		return modelAndView;
	}
}
