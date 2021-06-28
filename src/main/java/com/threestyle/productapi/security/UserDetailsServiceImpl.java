package com.threestyle.productapi.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")//objeto identificador
public class UserDetailsServiceImpl implements UserDetailsService { //apenas um método é implementado
	
	//é um método que serve para encontrar um usuário, caso não encontre lança uma exceção
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(username.equals("user")) {
			return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
		} else if(username.equals("admin")) {
			return User.withUsername(username).password(encoder.encode("admin")).roles("USER", "ADMIN").build();
		}
		
		throw new UsernameNotFoundException("user not found"); 
	}

}
