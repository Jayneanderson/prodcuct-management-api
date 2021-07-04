package com.threestyle.productapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.threestyle.productapi.service.ProdutoService;
import com.threestyle.productapi.model.Produto;


@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping()
	public ResponseEntity<List<Produto>> listAll() {
		/*
		 * return new
		 * ResponseEntity<Iterable<Produto>>(service.getProdutos(),HttpStatus.OK); este
		 * é um atalho para o de cima
		 */
		return ResponseEntity.ok(produtoService.getProdutos());
	}

	@GetMapping("/{idproduto}")
	public ResponseEntity<Produto> findById(@PathVariable("idproduto") Long id) {
		Produto produto = produtoService.getProdutoById(id);
		return ResponseEntity.ok(produto);
	}

	/*
	 * salva um objeto do tipo Produto, sendo que a anotação @RequestBody converte o
	 * Json para o objeto. ao insetir um produto, pegaremos a URI dela para fornecer
	 * como resposta
	 */
	@PostMapping
	@Secured({"ROLE_ADMIN"}) //ao colocar essa anotação, eu n consigo usar mais usuário e senha padrões. Tire do properties
	public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto) {

		Produto produtoSalvo = produtoService.create(produto);
		URI uri = getUri(produtoSalvo.getId());
		return ResponseEntity.created(uri).build();

	}
	
	private URI getUri(Long id) {
		
		return ServletUriComponentsBuilder
				// pega o caminho padrão para inserir um produto ("/api/v1/produtos")
				.fromCurrentRequest()
				// acrescenta o id na URI ("/api/v1/produtos/id");
				.path("/{id}")
				// pega o id do objeto salvo acima;
				.buildAndExpand(id)
				// converte tudo em URI.
				.toUri();
	}

	/*
	 * o método do put é mais complexo. Preste atenção. Com este mapeamento, é
	 * possível passar o id que será atualizado na própria URL
	 */
	@PutMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Produto> update(@RequestBody @Valid Produto produto, @PathVariable("id") Long id) {
		Produto produtoAtualizado = produtoService.update(produto, id);
		return ResponseEntity.ok(produtoAtualizado);
	}

	@DeleteMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity delete(@PathVariable("id") Long id) {
		produtoService.delete(id);
		return ResponseEntity.ok().build();
	}

}