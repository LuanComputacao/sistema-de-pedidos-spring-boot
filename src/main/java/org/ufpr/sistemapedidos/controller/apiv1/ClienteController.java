package org.ufpr.sistemapedidos.controller.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ufpr.sistemapedidos.model.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
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

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente cliente1 = null;
            cliente1 = clienteRepository.findOne(cliente.getId());
            if (cliente1.getId() != null)
                return new ResponseEntity<>(clienteRepository.save(cliente), OK);
            return new ResponseEntity<>(clienteRepository.save(cliente), CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>((Cliente) null, CONFLICT);
        }
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
}
