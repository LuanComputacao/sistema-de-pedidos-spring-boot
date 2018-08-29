package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.repository.ProdutoRepository;

@Controller
public class Produtos {

    private final String view = "produtos";

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public ModelAndView produtos() {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("listar", true);
        mv.addObject("produtos", produtoRepository.findAll());
        return mv;
    }

    @GetMapping("/produto")
    public ModelAndView produto() {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("criar", true);
        return mv;
    }

    @GetMapping("/produto/{id}")
    public ModelAndView prudot(@PathVariable(name = "id") Integer produtoID) {
        ModelAndView mv = new ModelAndView(view);
        mv.addObject("editar", true);
        mv.addObject("produto", produtoRepository.findOne(produtoID));
        return mv;
    }
}
