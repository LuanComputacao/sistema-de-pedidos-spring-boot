package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.services.ClienteService;

@Controller
public class ClientesController {

    private final String view = "clientes";

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public ModelAndView clientes() {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("listar", true);
        mv.addObject("clientes", this.clienteService.listarClientes());
        return mv;
    }

    @GetMapping({"/cliente"})
    public ModelAndView cliente() {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("criar", true);
        return mv;
    }

    @GetMapping("/cliente/{id}")
    public ModelAndView cliente(@PathVariable(name = "id") Integer clienteID) {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("editar", true);
        mv.addObject("cliente", this.clienteService.encontrarPorID(clienteID));
        return mv;
    }
}
