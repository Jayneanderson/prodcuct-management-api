package com.threestyle.productapi.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //permite capturar alguns eventos rest, inclusive as exceções
public class ExceptionConfig {
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class
	})//serve para mapear o tipo da exception para o método. É possível mapear + de uma colocando vírgula e a exceção
	public ResponseEntity errorNotFound(Exception exception) { //a execeção é pega no parâmetro
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity errorBadRequest(Exception exception) {
		return ResponseEntity.notFound().build();
	}
}
