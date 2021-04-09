package com.threestyle.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.threestyle.productapi.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
