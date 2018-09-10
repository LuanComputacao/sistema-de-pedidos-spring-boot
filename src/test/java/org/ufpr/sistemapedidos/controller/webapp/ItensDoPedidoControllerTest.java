package org.ufpr.sistemapedidos.controller.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.ufpr.sistemapedidos.domain.ItemDoPedido;
import org.ufpr.sistemapedidos.domain.ItemDoPedidoPK;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.domain.Produto;
import org.ufpr.sistemapedidos.services.ItemDoPedidoService;
import org.ufpr.sistemapedidos.services.PedidoService;
import org.ufpr.sistemapedidos.services.ProdutoService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItensDoPedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProdutoService produtoService;

    @MockBean
    ItemDoPedidoService itemDoPedidoService;

    @MockBean
    PedidoService pedidoService;

    @Test
    public void contexLoads() throws Exception {

    }

    @Test
    public void itensDoPedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        when(pedidoService.encontrarPorId(1))
                .thenReturn(pedido);

        this.mockMvc
                .perform(get("/pedido/1/itens"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("listar", true))
                .andExpect(model().attribute("pedido", pedido));
    }


    @Test
    public void chooseItemDoPedido1() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);

        Produto produto = new Produto();
        produto.setId(1);
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        ItemDoPedidoPK itemDoPedidoPK = new ItemDoPedidoPK(pedido.getId(), produto.getId());
        ItemDoPedido itemDoPedido = new ItemDoPedido(itemDoPedidoPK);

        when(pedidoService.encontrarPorId(1))
                .thenReturn(pedido);
        when(itemDoPedidoService.encontrarPorPk(itemDoPedidoPK))
            .thenReturn(itemDoPedido);


        this.mockMvc
                .perform(get("/pedido/1/produto/1/item"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("editar", true))
                .andExpect(model().attribute("pedido", pedido))
                .andExpect(model().attribute("itemDoPedido", itemDoPedido));
    }
}