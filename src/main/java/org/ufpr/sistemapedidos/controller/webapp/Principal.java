package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by luancomputacao on 24/02/18.
 */
@Controller
public class Principal {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/itens-do-pedido")
    public ModelAndView itensDoPedido() {
        return new ModelAndView("itens-do-pedido");
    }
}
