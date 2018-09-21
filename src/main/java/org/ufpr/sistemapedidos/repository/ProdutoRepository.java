package org.ufpr.sistemapedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.sistemapedidos.domain.Produto;
import org.ufpr.sistemapedidos.domain.ItemDoPedido;
import org.ufpr.sistemapedidos.domain.ItemDoPedidoPK;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT c FROM Produto c WHERE c.id = ?1")
    Produto findOne(Integer produtoId);

    @Query("SELECT c FROM Produto c WHERE c.descricao LIKE ?1 ORDER BY c.id DESC")
    List<Produto> findTop1ByDescription(String descricao);

    @Query("SELECT c FROM Produto c WHERE c.id NOT IN(SELECT c.itemDoPedidoCollection. FROM  d WHERE d.id_pedido = ?1)")
    List<Produto> findAvailableToInclude(Integer idPedido);

}
