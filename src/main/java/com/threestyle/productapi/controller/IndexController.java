package com.threestyle.productapi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/produtos")
public class IndexController {
	
	@GetMapping("/userinfo")
	public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {//A notação permite injetar UserDetails para pegar as informações dos usuários que o spring converte para Json
		return user;
	}

}