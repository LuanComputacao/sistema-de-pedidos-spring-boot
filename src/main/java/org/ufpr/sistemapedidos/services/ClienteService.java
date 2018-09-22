package org.ufpr.sistemapedidos.services;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.ClienteDTO;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente encontrarPorID(Integer clienteID) {
        return clienteRepository.findOne(clienteID).orElse(null);
    }

    public Cliente encontrarPorCpf(String clienteCpf) {
        return clienteRepository.findByCpf(clienteCpf).orElse(null);
    }

    public Cliente criarCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteFound = clienteRepository.findByCpf(clienteDTO.getCpf());
        if (clienteDTO.getId() != null || clienteFound.isPresent()) {
            return null;
        }
        return clienteRepository.save(new Cliente(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getSobrenome()));
    }

    public Cliente editarCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteDTO.getId());
        Optional<Cliente> clienteOptional1 = clienteRepository.findByCpf(clienteDTO.getCpf());
        if (clienteOptional.isPresent() && !clienteOptional1.isPresent()) {
            Cliente cliente = new Cliente(clienteDTO);
            cliente.setId(clienteOptional.get().getId());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public Boolean deletaCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpfAndId(clienteDTO.getId(), clienteDTO.getCpf());
        clienteOptional.ifPresent(cliente -> clienteRepository.delete(cliente));
        return !clienteRepository.findByCpfAndId(clienteDTO.getId(), clienteDTO.getCpf()).isPresent();
    }
}
