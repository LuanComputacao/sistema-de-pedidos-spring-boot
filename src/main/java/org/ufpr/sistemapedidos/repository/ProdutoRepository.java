package org.ufpr.sistemapedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.ufpr.sistemapedidos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT c FROM Produto c WHERE c.id = ?1")
    Produto findOne(Integer produtoId);
}
