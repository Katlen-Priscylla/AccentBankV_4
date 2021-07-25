package com.accenture.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Endereco;
import com.accenture.bank.servico.AgenciaService;
import com.accenture.bank.servico.CepService;
import com.accenture.bank.servico.EnderecoService;

@RestController
@RequestMapping("agencias/")
public class AgenciaController {
//	@Autowired
//	private CepService cepService;

	@Autowired
	private AgenciaService agenciaService;

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping
	public Agencia save( @RequestBody Agencia agencia) {

		//Endereco enderecoAgencia = cepService.buscaEnderecoPorCep("53610530");


		return agenciaService.saveAgencia(agencia);
	}

	@GetMapping
	public List<Agencia> findAll() {
		return agenciaService.getAllAgencias();
	}

	@GetMapping("{id}")
	public Agencia findId(@PathVariable Long id) {
		return agenciaService.getById(id);
	}

	@PutMapping("{id}")
	public Agencia atualizaAgencia(@PathVariable Long id, @RequestBody Agencia agencia) {
		return agenciaService.atualizaAgencia(id, agencia);
	}

	@DeleteMapping("{id}")
	public void deletaById(@PathVariable Long id) {
		agenciaService.deletaAgencia(id);
	}

}
