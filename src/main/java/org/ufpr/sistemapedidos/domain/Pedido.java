package org.ufpr.sistemapedidos.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Created by luancomputacao on 24/02/18.
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", length = 11)
    private Integer id;

    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    @JsonBackReference
    private Collection<ItemDoPedido> itemDoPedidoCollection;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id", updatable = false, nullable = false)
    @JsonManagedReference
    @ManyToOne
    private Cliente cliente;

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
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId()) &&
                Objects.equals(getDataPedido(), pedido.getDataPedido()) &&
                Objects.equals(getCliente(), pedido.getCliente());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDataPedido(), getCliente());
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", itemDoPedidoCollection=" + itemDoPedidoCollection +
                ", cliente=" + cliente +
                '}';
    }
}
