package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.PedidoWrapper;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.domain.Pedido;
import org.ufpr.sistemapedidos.repository.ClienteRepository;
import org.ufpr.sistemapedidos.repository.PedidoRepository;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") Integer pedidoId) {

        Pedido pedido = pedidoRepository.findOne(pedidoId);

        if (pedido == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(pedido);
    }


    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pedido> createPedido(@Valid @RequestBody PedidoWrapper pedidoWrapper) {
        try {
            Pedido pedido = pedidoRepository.findOne(pedidoWrapper.getId());

            if (pedido != null) {
                return new ResponseEntity<>(pedidoRepository.save(pedido), OK);
            }

            pedido = new Pedido();
            Cliente cliente;
            cliente = clienteRepository.findOne(pedidoWrapper.getClienteID());
            pedido.setDataPedido(pedidoWrapper.getDataPedido());
            pedido.setCliente(cliente);

            pedido = pedidoRepository.save(pedido);

            return new ResponseEntity<>(pedido, CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>((Pedido) null, CONFLICT);
        }
    }

    /**
     * Deleta um Pedido.
     *
     * @param pedidoWrapper Representação simplificada de um Pedido
     * @return Instancia de ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(
            @PathVariable(value = "id") Integer pedidoId,
            @Valid @RequestBody PedidoWrapper pedidoWrapper) {
        Pedido pedido = pedidoRepository.findOne(pedidoWrapper.getId());

        if (pedido != null
                && pedido.getCliente().getId().equals(pedidoWrapper.getClienteID())
                && pedido.getDataPedido().equals(pedidoWrapper.getDataPedido())) {
            pedidoRepository.delete(pedido);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
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
