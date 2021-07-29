package com.accenture.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TransacaoInvalidaException extends RuntimeException{

	public TransacaoInvalidaException(String message) {
		super(message);
	}
}
