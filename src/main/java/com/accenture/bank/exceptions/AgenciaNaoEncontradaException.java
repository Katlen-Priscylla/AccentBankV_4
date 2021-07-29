package com.accenture.bank.exceptions;

public class AgenciaNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgenciaNaoEncontradaException() {
		super("Agencia Nao Encontrada");
	}
}
