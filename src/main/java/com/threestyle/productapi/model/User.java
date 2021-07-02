package com.threestyle.productapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45)
	@NotBlank(message = "O nome não pode ser nulo")
	private String nome;
	
	@Column(length = 45)
	@NotBlank(message = "O login não pode ser vazio")
	private String login;
	
	@Column(length = 45, nullable = false)
	@Size(min = 8)
	@NotBlank(message = "a senha precisa ter 8 caracteres")
	private String senha;
	
	@Column(length = 100)
	@NotBlank(message = "O email é inválido")
	private String email;
}
