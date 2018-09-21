package org.ufpr.sistemapedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.domain.Produto;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }


    public List<Produto> listarProdutosNaoIncluidosNoPedido(Integer idPedido){
        return produtoRepository.findAvailableToInclude(idPedido);
    }
}
