package com.threestyle.productapi.service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.threestyle.productapi.controller.upload.UploadInput;

@Service
public class FirebaseStorageService {
	
	/*Anotação para ser chamado apenas uma vez quando
	 * a aplicação sobe pela 1ª vez. Ou seja, o método
	 * faz inicialização da aplicação do firebase.
	 * */
	@PostConstruct
	private void init() throws IOException {
		if(FirebaseApp.getApps().isEmpty()) {
		/*verifica se a lista de apps do firebase está vazia para
		 * não correr risco da aplicação rodar duas vezes.
		 * A lista é justamente o arquivo de configuração accountKey,
		 * pois estou trabalhando com um projeto só apenas.
		 */
		//estou pegando o recurso como uma stream para ser lido
		InputStream in = FirebaseStorageService.class.getResourceAsStream("/serviceAccountKey.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
		//aqui eu passo essas informações para as credenciais do Google
				.setCredentials(GoogleCredentials.fromStream(in))
				.setStorageBucket("people-api-c1.appspot.com")//pasta que os arquivos serão salvos
				.build();
		
		FirebaseApp.initializeApp(options);
		}
		
	}
	
	public String upload(UploadInput uploadInput) {
		
		Bucket bucket = StorageClient.getInstance().bucket();
		System.out.println(bucket);
		
		//convertendo o base64 para bytes
		byte[] bytes = java.util.Base64.getDecoder().decode(uploadInput.getBase64());
		
		//pego o nome do arquivo
		String fileName = uploadInput.getFileName();

		Blob blob = bucket.create(fileName, bytes, uploadInput.getMimeType());
		
		//deixa o arquivo visível e público com permissão de leitura
		blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
		
		//aqui eu retorno a url com o padrão
		return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
	}
	
//	Lembre-se de testar a biblioteca Gson para converter em Json
//
//	Dependência
//
//	<dependency>
//	  <groupId>com.google.code.gson</groupId>
//	  <artifactId>gson</artifactId>
//	  <version>2.8.8</version>
//	</dependency>
//
//	Como fazer?
//
//	Instancie um objeto do tipo Gson depois faça:
//
//	objetoGson.toJson(url);
}
