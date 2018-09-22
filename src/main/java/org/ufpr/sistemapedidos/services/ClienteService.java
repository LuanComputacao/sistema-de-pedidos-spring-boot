package org.ufpr.sistemapedidos.services;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ufpr.sistemapedidos.controller.apiv1.wrapper.ClienteDTO;
import org.ufpr.sistemapedidos.domain.Cliente;
import org.ufpr.sistemapedidos.repository.ClienteRepository;
import org.ufpr.sistemapedidos.utils.CpfUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * Avalia se o Nome é válido
     * <p>
     * - mais de um caractere válido
     *
     * @param nome Nome para avaliação
     * @return Verdadeiro se válido
     */
    Boolean validaNome(String nome) {
        return nome.length() > 1;
    }

    /**
     * Verifica se o CPF é válido ou não
     *
     * @param cpf CPF para ser avaliado
     * @return Verdadeiro se válido
     */
    Boolean validaCpf(String cpf) {
        return CpfUtils.isCPF(cpf);
    }

    Boolean verificaDados(String cpf, String nome, String sobrenome) {
        return (cpf.length() == 11 && this.validaNome(nome) && this.validaNome(sobrenome));
    }

    public List<Cliente> listarClientes() {
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
        if (clienteDTO.getId() != null
                || clienteFound.isPresent()
                || !this.verificaDados(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getSobrenome())) {
            return null;
        }
        return clienteRepository.save(new Cliente(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getSobrenome()));
    }

    public Cliente editarCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteParaEditar = clienteRepository.findById(clienteDTO.getId());
        Optional<Cliente> clienteFound = clienteRepository.findByCpf(clienteDTO.getCpf());
        Boolean podeSerEditado = false;
        if (clienteParaEditar.isPresent()
                && this.verificaDados(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getSobrenome())) {

            if (clienteFound.isPresent()) {
                if (clienteParaEditar.get().getCpf().equals(clienteFound.get().getCpf())
                        && clienteParaEditar.get().getId().equals(clienteFound.get().getId())) {

                    podeSerEditado = true;
                }
            } else {
                podeSerEditado = true;
            }
        }

        if (podeSerEditado) {
            Cliente cliente = new Cliente(clienteDTO);
            cliente.setId(clienteParaEditar.get().getId());
            Cliente saved = clienteRepository.save(cliente);
            return saved;
        } else {
            return null;
        }
    }

    public Boolean deletaCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpfAndId(clienteDTO.getId(), clienteDTO.getCpf());
        clienteOptional.ifPresent(cliente -> clienteRepository.delete(cliente));
        return !clienteRepository.findByCpfAndId(clienteDTO.getId(), clienteDTO.getCpf()).isPresent();
    }
}
