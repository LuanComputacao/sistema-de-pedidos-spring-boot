package org.ufpr.sistemapedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.sistemapedidos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("SELECT c FROM Pedido c WHERE c.id = ?1")
    Pedido findOne(Integer pedidoID);

}
