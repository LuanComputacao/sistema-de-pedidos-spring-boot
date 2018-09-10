package org.ufpr.sistemapedidos.controller.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.services.ClienteService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void contextLoads() throws Exception {

    }

    @Test
    public void clientes() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add( new Cliente("00000000001", "Luan", "Santana"));

        when(clienteService.listarClientes())
                .thenReturn(clientes);

        this.mockMvc
                .perform(get("/clientes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("clientes"))
                .andExpect(model().attribute("listar", true))
                .andExpect(model().attribute("clientes", clientes));
    }

    @Test
    public void cliente() throws Exception{
        this.mockMvc
                .perform(get("/cliente"))
                .andDo(print())
                .andExpect(view().name("clientes"))
                .andExpect(model().attribute("criar", true));
    }

    @Test
    public void clienteId() throws Exception{

        Cliente cliente = new Cliente("00000000001", "Luan", "Santana");
        cliente.setId(1);

        when(clienteService.encontrarPorID(1))
                .thenReturn(cliente);

        this.mockMvc
                .perform(get("/cliente/1"))
                .andDo(print())
                .andExpect(view().name("clientes"))
                .andExpect(model().attribute("editar", true))
                .andExpect(model().attribute("cliente", cliente));
    }
}