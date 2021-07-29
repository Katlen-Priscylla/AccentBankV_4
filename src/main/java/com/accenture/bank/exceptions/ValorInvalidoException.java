package com.accenture.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValorInvalidoException extends RuntimeException {
	
	public ValorInvalidoException(String message) {
		super(message);
	}

}
