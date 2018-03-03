package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.model.Produto;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable(value = "id") Integer produtoId) {
        Produto produto = produtoRepository.findOne(produtoId);
        if (produto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Produto> createProduto(@Valid @RequestBody Produto produto) {
        try {
            List<Produto> produtos = null;
            produtos = produtoRepository.findTop1ByDescription(produto.getDescricao());
            System.out.println(produto);
            if (!produtos.isEmpty())
                return new ResponseEntity<>(produtoRepository.save(produtos.get(0)), OK);
            return new ResponseEntity<>(produtoRepository.save(produto), CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>((Produto) null, CONFLICT);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable(value = "id") Integer produtoId,
                                                 @Valid @RequestBody Produto produtoDetails) {

        Produto produto = produtoRepository.findOne(produtoId);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        produto.setDescricao(produtoDetails.getDescricao());

        Produto updatedProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }
}
