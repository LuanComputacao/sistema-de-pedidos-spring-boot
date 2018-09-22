package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.ProdutoDTO;
import org.ufpr.sistemapedidos.domain.Produto;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Produto> createProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {

        try {
            Produto produto = new Produto();
            List<Produto> produtos = new ArrayList<Produto>();
            boolean criar = false;
            boolean update = false;

            if (produtoDTO.getId() != null){
                produto = produtoRepository.findOne(produtoDTO.getId());
            }

            if (produtoDTO.getDescricao() != null && !"".equals(produtoDTO.getDescricao())){
                produtos = produtoRepository.findTop1ByDescription(produtoDTO.getDescricao());
            }

            if ((produto.getId() != null && produto.getDescricao() != null) || !produtos.isEmpty()) {
                update = true;
            } else {
                criar = true;
            }

            if (criar) {
                produto.setDescricao(produtoDTO.getDescricao());
                return new ResponseEntity<>(produtoRepository.save(produto), CREATED);
            }

            if (update){
                if (!produtos.isEmpty()) produto = produtos.get(0);
                produto.setDescricao(produtoDTO.getDescricao());
                return new ResponseEntity<>(produtoRepository.save(produto), OK);
            }
            return new ResponseEntity<>(produtoRepository.save(produto), BAD_REQUEST);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>((Produto) null, CONFLICT);
        }
    }

    /**
     * Remove um produto.
     *
     * @param produtoId     ID do Produto
     * @param produtoDTO Representação do Produto em JSON
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto(@PathVariable(value = "id") Integer produtoId,
                                            @Valid @RequestBody ProdutoDTO produtoDTO) {
        try {
            Produto produto = null;
            if (produtoId.equals(produtoDTO.getId())) {
                produto = produtoRepository.findOne(produtoId);
            }
            if (produto != null && produto.getDescricao().equals(produtoDTO.getDescricao())) {
                produtoRepository.delete(produto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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
