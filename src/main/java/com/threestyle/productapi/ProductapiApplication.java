package com.threestyle.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductapiApplication.class, args);
	}
//	saveData();
//	private static UserRepository userRepository;
//	
//	public ProductapiApplication(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	public static void saveData() {
//		User user = new User();
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		user.setEmail("jay@gmail.com");
//		user.setLogin("jay");
//		user.setNome("Jayneanderson");
//		user.setSenha(encoder.encode("24685"));
//		
//		userRepository.save(user);
//		System.out.println("Usuário slavo");
//	}

}
