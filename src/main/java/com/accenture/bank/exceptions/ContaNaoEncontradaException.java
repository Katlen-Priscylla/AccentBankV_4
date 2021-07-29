package com.accenture.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContaNaoEncontradaException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradaException() {
		super("Conta nao encontrada");
	}
}
