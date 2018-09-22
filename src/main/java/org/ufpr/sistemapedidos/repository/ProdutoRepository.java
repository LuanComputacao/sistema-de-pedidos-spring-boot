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

    @Query("SELECT p FROM Produto p WHERE p.id = ?1")
    Produto findOne(Integer produtoId);

    @Query("SELECT p FROM Produto p WHERE p.descricao LIKE ?1 ORDER BY p.id DESC")
    List<Produto> findTop1ByDescription(String descricao);

    @Query("SELECT p FROM Produto p WHERE p.id NOT IN (SELECT ip.itemDoPedidoPK.idProduto FROM  ItemDoPedido ip WHERE ip.itemDoPedidoPK.idPedido = ?1)")
    List<Produto> findAvailableToInclude(Integer idPedido);

}
