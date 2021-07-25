package com.accenture.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.servico.AgenciaService;

@RestController
//@RequestMapping("/agencias")
public class AgenciaController {
	@Autowired
	AgenciaService agenciaService;
	
	
	@RequestMapping(value = "/agencias", method =  RequestMethod.POST)
	@PostMapping
	public Agencia save( @Valid @RequestBody Agencia agencia) {
		return agenciaService.saveAgencia(agencia);
	}
}
