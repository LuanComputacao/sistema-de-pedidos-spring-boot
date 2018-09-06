package org.ufpr.sistemapedidos.builders;

import org.ufpr.sistemapedidos.domain.Cliente;

public class ClienteBuilder {

    private Cliente cliente;

    private ClienteBuilder(Cliente cliente) {
        this.cliente = cliente;
    }
}
