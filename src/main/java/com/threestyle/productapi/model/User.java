package com.threestyle.productapi.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class User implements UserDetails{ //implementando a UserDetails para retornar uma UserDetails lá no método loadUserByUsername 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 45)
	@NotBlank(message = "O nome não pode ser nulo")
	private String nome;
	
	@Column(name = "login", length = 45, unique = true)
	@NotBlank(message = "O login não pode ser vazio")
	private String login;
	
	@Column(name = "senha", length = 255, nullable = false)
	@Size(min = 5)
	@NotBlank(message = "a senha precisa ter 5 caracteres")
	private String senha;
	
	@Column(name = "email", length = 100, unique = true)
	@NotBlank(message = "O email é inválido")
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles"
	, joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
	, inverseJoinColumns = @JoinColumn(name = "role_id"
	, referencedColumnName = "id"))
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
