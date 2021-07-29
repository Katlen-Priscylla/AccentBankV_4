package com.accenture.bank.exceptions;

public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ClienteNotFoundException() {
		super("cliente nao encontrado");
	}
	
}

	
	

	