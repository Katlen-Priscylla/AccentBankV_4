package com.accenture.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.servico.ContaCorrenteService;

@RestController
public class ContaCorrenteController {

	@Autowired
	ContaCorrenteService contaCorrenteService;
	
	@RequestMapping(value = "/contas", method =  RequestMethod.POST)
	public ContaCorrente save( @Valid @RequestBody ContaCorrente contaCorrente) {
		return contaCorrenteService.saveConta(contaCorrente);
	}
}
