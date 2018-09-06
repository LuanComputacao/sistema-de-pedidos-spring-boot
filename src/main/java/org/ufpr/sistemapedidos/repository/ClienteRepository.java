package org.ufpr.sistemapedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.sistemapedidos.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.id = ?1")
    Cliente findOne(Integer clienteId);

    @Query("SELECT c FROM Cliente c WHERE c.cpf = ?1")
    Cliente findByCpf(String cpf);
}
