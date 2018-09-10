package org.ufpr.sistemapedidos.controller.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.services.PedidoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PedidosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void contextLoads() throws Exception {

    }

    @Test
    public void pedidos() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);

        when(pedidoService.listarPedidos())
                .thenReturn(pedidos);

        this.mockMvc
                .perform(get("/pedidos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("listar", true))
                .andExpect(model().attribute("pedidos", pedidos));
    }

    @Test
    public void pedido() {
    }

    @Test
    public void pedido1() {
    }
}