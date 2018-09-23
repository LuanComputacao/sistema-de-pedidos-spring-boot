package org.ufpr.sistemapedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.PedidoDTO;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

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

    public Boolean deletaPedido(PedidoDTO pedidoDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoDTO.getId());
        pedidoOptional.ifPresent(pedido -> {
            if (pedido.getItemDoPedidoCollection().isEmpty())
                pedidoRepository.delete(pedido);
        });
        return !pedidoRepository.findById(pedidoDTO.getId()).isPresent();
    }
}
