package com.threestyle.productapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //habilita a segurança de web para poder sobrescrever o métudo configure para que eu possa criar minhas próprias configurações e não mais as padrões do Spring Sec
@EnableGlobalMethodSecurity(securedEnabled = true) //habilitando a segurança por método. Agora precisamos criar os usuários manualmente dentro da classe SecurityConfig, ou seja, aqui
public class SecurityConfig extends WebSecurityConfigurerAdapter { //herdando a classe de configuração do spring
	
	//estamos utilizando aqui pq no spring, ao utilizar um componente, precisamos dizer aqui
	@Autowired
	//caso acuse erro é porque não está encontrado um bean com esse id do qualifier. Quando for fazer a injeção, utilize o objeto que possui este identificador
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
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
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//cria o objeto para criar o encoder
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		
//			agora não preico mais desses carinhas aqui em baixo
//			.inMemoryAuthentication().passwordEncoder(encoder) //ativa a autenticação em memória e o encoder das senhas
//			.withUser("user").password(encoder.encode("user")).roles("USER").and()
//			.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
			
	}
}
