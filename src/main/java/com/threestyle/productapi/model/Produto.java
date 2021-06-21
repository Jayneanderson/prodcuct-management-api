package com.threestyle.productapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.threestyle.productapi.enums.TipoProduto;

import lombok.Data;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45)
	@NotBlank(message = "O nome não pode ser nulo")
	private String nome;

	@Column(length = 25, nullable = false, name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoProduto tipo;

}