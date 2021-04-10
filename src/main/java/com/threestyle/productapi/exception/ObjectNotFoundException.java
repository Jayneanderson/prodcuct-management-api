package com.threestyle.productapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)//converte a exceção para status code
public class ObjectNotFoundException extends RuntimeException {
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
}
