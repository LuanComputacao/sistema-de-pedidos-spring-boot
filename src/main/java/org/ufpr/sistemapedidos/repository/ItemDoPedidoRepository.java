package org.ufpr.sistemapedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.sistemapedidos.model.ItemDoPedido;
import org.ufpr.sistemapedidos.model.ItemDoPedidoPK;
import org.ufpr.sistemapedidos.model.Pedido;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, ItemDoPedidoPK> {

    @Query("SELECT c FROM ItemDoPedido c WHERE c.itemDoPedidoPK = ?1")
    ItemDoPedido findOne(ItemDoPedidoPK itemDoPedidoPK);
}
