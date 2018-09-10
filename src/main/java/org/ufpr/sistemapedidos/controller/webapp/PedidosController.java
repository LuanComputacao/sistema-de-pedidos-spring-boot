package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.repository.ClienteRepository;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;
import org.ufpr.sistemapedidos.services.PedidoService;

import java.util.List;

@Controller
public class PedidosController {

    private final String viewPedidos = "pedidos";

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/pedidos")
    public ModelAndView pedidos() {
        ModelAndView mv = new ModelAndView(viewPedidos);

        mv.addObject("listar", true);

        List<Pedido> pedidos = pedidoService.listarPedidos();
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
        mv.addObject("pedido", pedidoService.encontrarPorId(pedidoID));
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    }
}
