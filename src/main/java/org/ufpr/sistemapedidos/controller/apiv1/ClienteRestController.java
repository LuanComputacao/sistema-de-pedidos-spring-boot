package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.ClienteWrapper;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteRestController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteWrapper clienteWrapper) {
        try {
            Cliente cliente1 = null;
            if (clienteWrapper.getId() != null)
                cliente1 = clienteRepository.findOne(clienteWrapper.getId());
            else if (clienteWrapper.getCpf() != null)
                cliente1 = clienteRepository.findByCpf(clienteWrapper.getCpf());
            if (cliente1 != null) {
                return new ResponseEntity<>(cliente1, OK);
            } else {
                cliente1 = new Cliente(clienteWrapper.getCpf(), clienteWrapper.getNome(), clienteWrapper.getSobrenome());
                return new ResponseEntity<>(clienteRepository.save(cliente1), CREATED);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>((Cliente) null, CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "id") Integer clienteId) {
        Cliente cliente = clienteRepository.findOne(clienteId);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> getClienteByCpf(@PathVariable(value = "cpf") String clienteCpf) {
        Cliente cliente = clienteRepository.findByCpf(clienteCpf);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable(value = "id") Integer clienteId,
                                                 @Valid @RequestBody Cliente clienteDetails) {

        Cliente cliente = clienteRepository.findOne(clienteId);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        cliente.setCpf(clienteDetails.getCpf());
        cliente.setNome(clienteDetails.getNome());
        cliente.setSobrenome(clienteDetails.getSobrenome());

        Cliente updatedCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteNote(@PathVariable(value = "id") Integer clienteId,
                                             @Valid @RequestBody ClienteWrapper clienteWrapper) {
        System.out.println(clienteWrapper);
        try {
            Optional<Cliente> optCliente = clienteRepository.findById(clienteId);
            Cliente cliente = new Cliente();
            if (optCliente.isPresent())
                cliente = optCliente.get();

            if (cliente.getId() != null && cliente.getCpf().equals(clienteWrapper.getCpf())) {
                clienteRepository.delete(cliente);
                return ResponseEntity.accepted().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }
}
