package org.ufpr.sistemapedidos.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by luancomputacao on 24/02/18.
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoPK pedidoPK;

    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<ItemDoPedido> itemDoPedidoCollection;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public PedidoPK getPedidoPK() {
        return pedidoPK;
    }

    public void setPedidoPK(PedidoPK pedidoPK) {
        this.pedidoPK = pedidoPK;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Collection<ItemDoPedido> getItemDoPedidoCollection() {
        return itemDoPedidoCollection;
    }

    public void setItemDoPedidoCollection(Collection<ItemDoPedido> itemDoPedidoCollection) {
        this.itemDoPedidoCollection = itemDoPedidoCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (!getPedidoPK().equals(pedido.getPedidoPK())) return false;
        if (getDataPedido() != null ? !getDataPedido().equals(pedido.getDataPedido()) : pedido.getDataPedido() != null)
            return false;
        return getCliente().equals(pedido.getCliente());
    }

    @Override
    public int hashCode() {
        int result = getPedidoPK().hashCode();
        result = 31 * result + (getDataPedido() != null ? getDataPedido().hashCode() : 0);
        result = 31 * result + getCliente().hashCode();
        return result;
    }
}
