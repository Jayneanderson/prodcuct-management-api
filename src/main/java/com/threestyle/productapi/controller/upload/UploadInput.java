package com.threestyle.productapi.controller.upload;

import lombok.Data;

@Data
public class UploadInput {
	
	private String fileName;
	
	private String base64; //tipo de formato 
	
	private String mimeType;
	
}
