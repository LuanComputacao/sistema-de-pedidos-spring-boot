package org.ufpr.sistemapedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.domain.ItemDoPedido;
import org.ufpr.sistemapedidos.domain.ItemDoPedidoPK;
import org.ufpr.sistemapedidos.repository.ItemDoPedidoRepository;

@Service
public class ItemDoPedidoService {

    @Autowired
    ItemDoPedidoRepository itemDoPedidoRepository;

    public ItemDoPedido encontrarPorPk(ItemDoPedidoPK itemDoPedidoPK){
        return itemDoPedidoRepository.findOne(itemDoPedidoPK);
    }
}
