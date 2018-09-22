package org.ufpr.sistemapedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.ProdutoDTO;
import org.ufpr.sistemapedidos.domain.Produto;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

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

    public Produto encontrarPorID(Integer produtoID) {
        return produtoRepository.findOne(produtoID);
    }

    public Boolean deletaProduto(ProdutoDTO produtoDTO) {
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoDTO.getId());
        produtoOptional.ifPresent(produto -> produtoRepository.delete(produto));
        return !produtoRepository.findById(produtoDTO.getId()).isPresent();
    }

}
