package com.threestyle.productapi.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice // permite capturar alguns eventos rest, inclusive as exceções
public class ExceptionConfig extends ResponseEntityExceptionHandler {//a classe extendida possui diversos métodos úteis para trabalharmos aqui
	
	// serve para mapear o tipo da exception para o método.
	// É possível mapear + de uma colocando vírgula e a
	// exceção
	// a execeção é pega no parâmetro
	@ExceptionHandler({EmptyResultDataAccessException.class}) 
	public ResponseEntity errorNotFound(Exception exception) { 
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity errorBadRequest(Exception exception) {
		return ResponseEntity.badRequest().build();
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("error", "Operação não permitida");
		return new ResponseEntity<Object>(responseBody, status.METHOD_NOT_ALLOWED);
	}
	

	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity handleAccessDenie()
			throws IOException, ServletException {
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado!"));
		
	}

//	@ExceptionHandler({ AccessDeniedException.class })
//	public ResponseEntity<Object> handleAcessDeniedException(Exception ex, WebRequest request) {
//		
//		String bodyResponse = "Cara, não vai rolar acesso!";
//		return new ResponseEntity<Object>(bodyResponse, new HttpHeaders(), HttpStatus.FORBIDDEN);
//	}

}

class Error {
	public String error;

	public Error(String error) {
		this.error = error;
	}
}
