package org.ufpr.sistemapedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido encontrarPorId(Integer pedidoId){
        return pedidoRepository.findOne(pedidoId);
    }

}
