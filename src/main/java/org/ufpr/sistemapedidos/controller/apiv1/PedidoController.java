package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.repository.PedidoRepository;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") Integer pedidoId) {

        Pedido pedido = pedidoRepository.findOne(pedidoId);

        if (pedido == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(pedido);
    }


//    @GetMapping("/{id}/cliente/{clienteId}")
//    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "clienteId") Integer clienteID, @PathVariable(value = "id") Integer pedidoId) {
//
//        Pedido pedido = pedidoRepository.findOne(pedidoId);
//        if (pedido == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok().body(pedido);
//    }

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pedido> createPedido(@Valid @RequestBody Pedido pedido) {
        try {
            Pedido pedido1 = pedidoRepository.findOne(pedido.getId());
            if (pedido1 != null) {
                return new ResponseEntity<>(pedidoRepository.save(pedido), OK);
            }
            return new ResponseEntity<>(pedidoRepository.save(pedido), CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>((Pedido) null, CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable(value = "id") Integer pedidoId,
                                               @Valid @RequestBody Pedido pedidoDetails) {

        Pedido pedido = pedidoRepository.findOne(pedidoId);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        pedido.setItemDoPedidoCollection(pedidoDetails.getItemDoPedidoCollection());
        pedido.setCliente(pedidoDetails.getCliente());
        pedido.setDataPedido(new java.util.Date());

        Pedido updatedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(updatedPedido);
    }
}
