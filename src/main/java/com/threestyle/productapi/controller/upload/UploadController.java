package com.threestyle.productapi.controller.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threestyle.productapi.service.FirebaseStorageService;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {
	
	@Autowired
	private FirebaseStorageService uploadService;//dependência para injetar o upload
	
	/*
	 * Este objeto recebe um objeto que será convertido para json UploadInput (nome, base64, tipo)
	 * Pega a String de upload utilizando o service
	 * Retorna o status code como OK
	 */
	@PostMapping
	public ResponseEntity upload(@RequestBody UploadInput uploadInput) {
		
		//chamando o método que faz
		String url = uploadService.upload(uploadInput);
		
		//Objeto feito para converter url em Json
		return ResponseEntity.ok(new UploadOutput(url));
	}
}
