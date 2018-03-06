package org.ufpr.sistemapedidos.controller.apiv1.wrapper;

import javax.persistence.Column;
import java.util.Date;

public class PedidoWrapper {
    private Integer id;
    private Date dataPedido;
    private Integer clienteID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Integer getClienteID() {
        return clienteID;
    }

    public void setClienteID(Integer clienteID) {
        this.clienteID = clienteID;
    }

    @Override
    public String toString() {
        return "PedidoWrapper{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", clienteID=" + clienteID +
                '}';
    }
}
