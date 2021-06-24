package com.threestyle.productapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //habilita a segurança de web para poder sobrescrever o métudo configure para que eu possa criar minhas próprias configurações e não mais as padrões do Spring Sec
public class SecurityConfig extends WebSecurityConfigurerAdapter { //herdando a classe de configuração do spring
	
	
	//vamos sobrescrever o método configure. Note que, por padrão qualquer request está autenticada
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().csrf().disable();
	}
	//A partir daqui eu estou deixando o basicAuthetication como modelo de autenticação da aplicação
	//ou seja, sem o form do Spring Boot
}
