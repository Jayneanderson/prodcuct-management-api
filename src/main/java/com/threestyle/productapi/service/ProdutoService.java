package com.threestyle.productapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.threestyle.productapi.exception.ObjectNotFoundException;
import com.threestyle.productapi.model.Produto;
import com.threestyle.productapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}

	public Produto getProdutoById(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
	}

	public Produto create(Produto produto) {
//		Este método é para impedir o usuário de informar o id
		Assert.isNull(produto.getId(), "Não foi possível inserir o produto");
		return produtoRepository.save(produto);
	}

	public Produto update(Produto produto, Long id) {
		
		getProdutoById(id);
		produto.setId(id);
		return produtoRepository.save(produto);
	}

	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

}
