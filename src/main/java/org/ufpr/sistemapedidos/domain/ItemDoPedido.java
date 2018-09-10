package org.ufpr.sistemapedidos.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_do_pedido")
public class ItemDoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ItemDoPedidoPK itemDoPedidoPK;

    @Column(name = "qtdade", length = 11)
    private Integer qtdade;


    @JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @JoinColumn(name = "id_produto", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonManagedReference
    @ManyToOne(optional = false)
    private Produto produto;

    public ItemDoPedido(ItemDoPedidoPK itemDoPedidoPK) {
        this.itemDoPedidoPK = itemDoPedidoPK;
    }

    public ItemDoPedido() {

    }


    public ItemDoPedidoPK getItemDoPedidoPK() {
        return itemDoPedidoPK;
    }

    public void setItemDoPedidoPK(ItemDoPedidoPK itemDoPedidoPK) {
        this.itemDoPedidoPK = itemDoPedidoPK;
    }

    public Integer getQtdade() {
        return qtdade;
    }

    public void setQtdade(Integer qtdade) {
        this.qtdade = qtdade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDoPedido)) return false;
        ItemDoPedido that = (ItemDoPedido) o;
        return Objects.equals(getItemDoPedidoPK(), that.getItemDoPedidoPK()) &&
                Objects.equals(getQtdade(), that.getQtdade()) &&
                Objects.equals(getPedido(), that.getPedido()) &&
                Objects.equals(getProduto(), that.getProduto());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getItemDoPedidoPK(), getQtdade(), getPedido(), getProduto());
    }

    @Override
    public String toString() {
        return "ItemDoPedido{" +
                "itemDoPedidoPK=" + itemDoPedidoPK +
                ", qtdade='" + qtdade + '\'' +
                ", produto=" + produto +
                '}';
    }
}
