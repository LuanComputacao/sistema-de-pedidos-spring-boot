package org.ufpr.sistemapedidos.model.impl;

import org.ufpr.sistemapedidos.model.Cliente;

public class ClienteImpl extends Cliente {

    public ClienteImpl(String cpf, String nome, String sobrenome) {
        super(cpf, nome, sobrenome);
    }

    public static Cliente createCliente(String cpf, String nome, String sobrenome){
        return new Cliente(cpf, nome, sobrenome);
    }
}
