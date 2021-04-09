package com.threestyle.productapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoProduto {
	
	ALIMENTOS("alimentos"),
	HIGIENE("higiene"),
	BEBIBAS("bebidas"),
	FRUTAS("frutas");
	
	
	private final String message;
	
}
