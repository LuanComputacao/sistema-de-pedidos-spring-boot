package org.ufpr.sistemapedidos.services;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Autowired
    @Spy
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    @DirtiesContext
    public void listarClientes_deveRetornarOsClientesInseridos() {
        Cliente cliente = new Cliente("00011122233", "Nome", "Sobrenome");
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(this.clienteRepository.findAll())
                .thenReturn(clientes);

        List<Cliente> givenClientes = clienteService.listarClientes();
        System.out.println(givenClientes.isEmpty());
//        Assert.assertThat(
//
//                        .iterator()
//                        .next(),
//                instanceOf(Cliente.class));
    }
}