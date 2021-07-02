package com.threestyle.productapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.threestyle.productapi.repository.UserRepository;

@Service(value = "userDetailsService")//objeto identificador
public class UserDetailsServiceImpl implements UserDetailsService { //apenas um método é implementado
	
	@Autowired
	UserRepository userRepository;
	//é um método que serve para encontrar um usuário, caso não encontre lança uma exceção
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		com.threestyle.productapi.model.User user = userRepository.findByLogin(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("user not found"); 
		}
		
		return User.withUsername(username).password(user.getSenha()).roles("USER").build();
	}

}
