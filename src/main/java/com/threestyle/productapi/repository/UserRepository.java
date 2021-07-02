package com.threestyle.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threestyle.productapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String usuario);
}
