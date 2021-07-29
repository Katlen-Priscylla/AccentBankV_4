package com.accenture.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FormatoDeContaInvalidoException extends RuntimeException {
	
	public FormatoDeContaInvalidoException(String message) {
		super(message);
	}

}
