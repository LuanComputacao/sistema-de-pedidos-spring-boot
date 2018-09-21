package org.ufpr.sistemapedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente encontrarPorID(Integer clienteID) {
        return clienteRepository.findOne(clienteID);
    }
}
