package com.threestyle.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threestyle.productapi.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String usuario);
}
