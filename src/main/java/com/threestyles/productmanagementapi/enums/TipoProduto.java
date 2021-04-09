package com.threestyles.productmanagementapi.enums;

public enum TipoProduto {
	
	ALIMENTOS("alimentos"),
	HIGIENE("higiene"),
	BEBIBAS("bebidas"),
	FRUTAS("frutas");
	
	
	private final String message;
	
	TipoProduto(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
