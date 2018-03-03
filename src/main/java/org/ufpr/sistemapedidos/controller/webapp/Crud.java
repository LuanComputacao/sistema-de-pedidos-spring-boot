package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

/**
 * Created by luancomputacao on 24/02/18.
 */
@Controller
public class Crud {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
