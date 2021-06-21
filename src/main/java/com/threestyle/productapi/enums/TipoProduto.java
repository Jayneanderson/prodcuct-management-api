package com.threestyle.productapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum TipoProduto {
	
	ALIMENTOS("alimentos"),
	HIGIENE("higiene"),
	BEBIBAS("bebidas"),
	FRUTAS("frutas");
	
	
	private final String message;
	
	private TipoProduto(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
