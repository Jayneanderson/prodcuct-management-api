package com.threestyle.productapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice //permite capturar alguns eventos rest, inclusive as exceções
public class ExceptionConfig extends ResponseEntityExceptionHandler{
	
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
		return ResponseEntity.badRequest().build();
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> responseBody =  new HashMap<>();
		responseBody.put("error", "Operação não permitida");
		return new ResponseEntity<Object>(responseBody, status.METHOD_NOT_ALLOWED);
	}
	
}
