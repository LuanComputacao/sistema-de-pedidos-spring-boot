package org.ufpr.sistemapedidos.controller.apiv1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.ufpr.sistemapedidos.model.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClienteRepository clienteRepository;



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllClientes() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/api/v1/clientes/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result);
    }

    @Test
    public void createCliente() {
    }

    @Test
    public void getClienteById() {
    }

    @Test
    public void getClienteByCpf() {
    }

    @Test
    public void updateCliente() {
    }

    @Test
    public void deleteNote() {
    }
}