package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

@Controller
public class Produtos {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public ModelAndView produtos() {
        ModelAndView mv = new ModelAndView("produtos");
        mv.addObject("listar", true);
        return mv;
    }


    @GetMapping("/produto")
    public ModelAndView produto() {
        ModelAndView mv = new ModelAndView("produtos");
        mv.addObject("criar", true);
        return mv;
    }
}
