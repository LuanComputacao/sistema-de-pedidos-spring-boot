package org.ufpr.sistemapedidos.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_do_pedido")
public class ItemDoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemDoPedidoPK itemDoPedidoPK;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Length(max = 11)
    @Column(name = "qtdade", length = 11)
    private String qtdade;


    @JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @JoinColumn(name = "id_produto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;


    public ItemDoPedidoPK getItemDoPedidoPK() {
        return itemDoPedidoPK;
    }

    public void setItemDoPedidoPK(ItemDoPedidoPK itemDoPedidoPK) {
        this.itemDoPedidoPK = itemDoPedidoPK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQtdade() {
        return qtdade;
    }

    public void setQtdade(String qtdade) {
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
        if (o == null || getClass() != o.getClass()) return false;

        ItemDoPedido that = (ItemDoPedido) o;

        if (getItemDoPedidoPK() != null ? !getItemDoPedidoPK().equals(that.getItemDoPedidoPK()) : that.getItemDoPedidoPK() != null)
            return false;
        if (!getId().equals(that.getId())) return false;
        if (getQtdade() != null ? !getQtdade().equals(that.getQtdade()) : that.getQtdade() != null) return false;
        return getPedido().equals(that.getPedido());
    }

    @Override
    public int hashCode() {
        int result = getItemDoPedidoPK() != null ? getItemDoPedidoPK().hashCode() : 0;
        result = 31 * result + getId().hashCode();
        result = 31 * result + (getQtdade() != null ? getQtdade().hashCode() : 0);
        result = 31 * result + getPedido().hashCode();
        return result;
    }
}
