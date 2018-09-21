package org.ufpr.sistemapedidos.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.ufpr.sistemapedidos.domain.ItemDoPedido;
import org.ufpr.sistemapedidos.domain.ItemDoPedidoPK;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.services.ItemDoPedidoService;
import org.ufpr.sistemapedidos.services.PedidoService;
import org.ufpr.sistemapedidos.services.ProdutoService;

import java.util.Optional;

@Controller
public class ItensDoPedidoController {

    private final String viewItens = "itens_do_pedido";

    @Autowired
    ItemDoPedidoService itensDoPedidoService;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/pedido/{id}/itens")
    public ModelAndView itensDoPedido(@PathVariable(name = "id") Integer pedidoID) {
        ModelAndView mv = new ModelAndView(viewItens);
        Pedido pedido = pedidoService.encontrarPorId(pedidoID);
        mv.addObject("listar", true);
        mv.addObject("pedido", pedido);
        return mv;
    }

    @GetMapping("/pedido/{id}/item")
    public ModelAndView itemDoPedido(@PathVariable(name = "id") Integer pedidoID) {
        ModelAndView mv = new ModelAndView(viewItens);
        Pedido pedido = pedidoService.encontrarPorId(pedidoID);
        mv.addObject("criar", true);
        mv.addObject("produtos", produtoService.listarProdutosNaoIncluidosNoPedido(pedidoID));
        mv.addObject("pedido", pedido);
        return mv;
    }

    @GetMapping("/pedido/{pedidoId}/produto/{produtoId}/item")
    public ModelAndView chooseItemDoPedido(
            @PathVariable(name = "pedidoId") Integer pedidoId,
            @PathVariable(name = "produtoId") Integer produtoId) {

        ModelAndView mv = new ModelAndView(viewItens);

        Pedido pedido = pedidoService.encontrarPorId(pedidoId);

        ItemDoPedidoPK itemDoPedidoPK = new ItemDoPedidoPK();
        itemDoPedidoPK.setIdPedido(pedido.getId());
        itemDoPedidoPK.setIdProduto(produtoId);

        ItemDoPedido itemDoPedido = new ItemDoPedido();
        try {
            Optional<ItemDoPedido> foundItemDoPedido = Optional.ofNullable(itensDoPedidoService.encontrarPorPk(itemDoPedidoPK));
            if (foundItemDoPedido.isPresent()) itemDoPedido = foundItemDoPedido.get();

            mv.addObject("editar", true);
            mv.addObject("pedido", pedido);
            mv.addObject("itemDoPedido", itemDoPedido);
            return mv;
        } catch (Exception ignored) {
            return mv;
        }

    }
}
