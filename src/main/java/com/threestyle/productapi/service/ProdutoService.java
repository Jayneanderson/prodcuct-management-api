package com.threestyle.productapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.threestyle.productapi.model.Produto;
import com.threestyle.productapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> getProdutoById(Long id) {
		return produtoRepository.findById(id);
	}


	public Produto create(Produto produto) {
//		Este método é para impedir o usuário de informar o id
		Assert.isNull(produto.getId(), "Não foi possível inserir o produto");
		return produtoRepository.save(produto);
	}

	public Produto update(Produto produto, Long produtoId) {

		if (produtoRepository.existsById(produtoId)) {
			produto.setId(produtoId);
			return produtoRepository.save(produto);
		}
		return null;
//		// verifica se o id não é nulo
//		Assert.notNull(produtoId, "Não foi possível atualizar o produto");
//
//		// Busca o produto no banco de dados
//		Optional<Produto> optional = getProdutoById(produtoId);
//
//		if (optional.isPresent()) {
//			Produto produtoDoBanco = optional.get();
//
//			
////		    abaixo 2eu pego as propriedades que eu atualizo no Insomnia e copio para o
////			objeto do banco, ou seja, substituo os dados
//			 
//			produtoDoBanco.setNome(produto.getNome());
//			produtoDoBanco.setTipo(produto.getTipo());
//
//			// aqui é só para exibir se deu certo
//			System.out.println("Carro id: " + produtoDoBanco.getId());
//
//			// salva no banco de dados
//			produtoRepository.save(produtoDoBanco);
//
//			return produtoDoBanco;
//
//		} else {
//			throw new RuntimeException("Não foi possível atualizar o produto");
//		}

	}

	public boolean delete(Long id) {

		if (getProdutoById(id).isPresent()) {
			produtoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
