package org.ufpr.sistemapedidos.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PedidoPK implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "id_cliente")
    private int idCliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoPK pedidoPK = (PedidoPK) o;

        return getId() == pedidoPK.getId() && getIdCliente() == pedidoPK.getIdCliente();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIdCliente();
        return result;
    }
}
