package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.ClienteDTO;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.services.ClienteService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteRestController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllClientes() {
        try {
            return ResponseEntity.ok(clienteService.listarClientes());
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {

        try {
            Cliente cliente = clienteService.criarCliente(clienteDTO);
            if (cliente != null) return new ResponseEntity<>(cliente, CREATED);
            return ResponseEntity.status(NOT_MODIFIED).build();
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "id") Integer clienteId) {

        try {
            Cliente cliente = clienteService.encontrarPorID(clienteId);
            if (cliente != null) return ResponseEntity.ok().body(cliente);
            return ResponseEntity.notFound().build();
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> getClienteByCpf(@PathVariable(value = "cpf") String clienteCpf) {

        try {
            Cliente cliente = clienteService.encontrarPorCpf(clienteCpf);
            if (cliente != null) return ResponseEntity.ok().body(cliente);
            return ResponseEntity.notFound().build();
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable(value = "id") Integer clienteId,
                                                 @Valid @RequestBody ClienteDTO clienteDTO) {

        try {
            if (!clienteId.equals(clienteDTO.getId())) return ResponseEntity.status(BAD_REQUEST).build();
            Cliente cliente = clienteService.editarCliente(clienteDTO);

            if (cliente == null) return ResponseEntity.status(NOT_FOUND).build();
            else return ResponseEntity.status(OK).body(cliente);
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteNote(@PathVariable(value = "id") Integer clienteId,
                                             @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Boolean clienteFoiDeletado = clienteService.deletaCliente(clienteDTO);
            if (clienteFoiDeletado) return ResponseEntity.accepted().build();
            else return ResponseEntity.notFound().build();
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }
}
