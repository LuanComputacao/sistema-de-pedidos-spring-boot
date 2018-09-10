package org.ufpr.sistemapedidos.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.domain.impl.ClienteImpl;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void whenFindByCpf_thenReturnCliente() {
        // given
        Cliente cliente = ClienteImpl.createCliente("00000000001", "Fist", "Last");


        testEntityManager.persist(cliente);
        testEntityManager.flush();

        // when
        Cliente found = clienteRepository.findByCpf(cliente.getCpf());

        // then
        assertEquals(found.getCpf(), cliente.getCpf());

    }
}
