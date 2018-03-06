package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.repository.ClienteRepository;
import org.ufpr.sistemapedidos.repository.PedidoRepository;

import java.util.List;

@Controller
public class Pedidos {

    private final String view = "pedidos";

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/pedidos")
    public ModelAndView pedidos() {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("listar", true);
        List<Pedido> pedidos = pedidoRepository.findAll();
        System.out.println("------------------DEBUG------------------");
        System.out.println(pedidos.get(0));
        System.out.println("------------------------------------");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
        System.out.println("------------------------------------");

        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping({"/pedido"})
    public ModelAndView pedido() {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("criar", true);
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    }

    @GetMapping("/pedido/{id}")
    public ModelAndView pedido(@PathVariable(name = "id") Integer pedidoID) {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("editar", true);
        mv.addObject("pedido", pedidoRepository.findOne(pedidoID));
        return mv;
    }
}
