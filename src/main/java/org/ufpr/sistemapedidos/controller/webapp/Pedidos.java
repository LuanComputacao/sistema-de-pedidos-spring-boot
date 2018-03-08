package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.model.Produto;
import org.ufpr.sistemapedidos.repository.ClienteRepository;
import org.ufpr.sistemapedidos.repository.PedidoRepository;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

import java.util.List;

@Controller
public class Pedidos {

    private final String viewPedidos = "pedidos";
    private final String viewItens = "itens_do_pedido";

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/pedidos")
    public ModelAndView pedidos() {
        ModelAndView mv = new ModelAndView(viewPedidos);

        mv.addObject("listar", true);

        List<Pedido> pedidos = pedidoRepository.findAll();
        mv.addObject("pedidos", pedidos);

        return mv;
    }

    @GetMapping({"/pedido"})
    public ModelAndView pedido() {
        ModelAndView mv = new ModelAndView(viewPedidos);
        mv.addObject("criar", true);
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;

    }

    @GetMapping("/pedido/{id}")
    public ModelAndView pedido(@PathVariable(name = "id") Integer pedidoID) {
        ModelAndView mv = new ModelAndView(viewPedidos);
        mv.addObject("editar", true);
        mv.addObject("pedido", pedidoRepository.findOne(pedidoID));
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    }


    @GetMapping("/pedido/{id}/itens")
    public ModelAndView itensDoPedido(@PathVariable(name = "id") Integer pedidoID) {
        ModelAndView mv = new ModelAndView(viewItens);
        Pedido pedido = pedidoRepository.findOne(pedidoID);
        mv.addObject("listar", true);
        mv.addObject("pedido", pedido);
        return mv;
    }

    @GetMapping("/pedido/{id}/item")
    public ModelAndView addItemDoPedido(@PathVariable(name = "id") Integer pedidoID) {
        ModelAndView mv = new ModelAndView(viewItens);

        Pedido pedido = pedidoRepository.findOne(pedidoID);
        List<Produto> produtos = produtoRepository.findAll();
        mv.addObject("criar", true);
        mv.addObject("pedido", pedido);
        mv.addObject("produtos", produtos);
        return mv;
    }
}
