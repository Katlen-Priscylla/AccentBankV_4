package com.accenture.bank.exceptions;

public class ContaNaoEncontradaException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradaException() {
		super("Conta nao encontrada");
	}
}
