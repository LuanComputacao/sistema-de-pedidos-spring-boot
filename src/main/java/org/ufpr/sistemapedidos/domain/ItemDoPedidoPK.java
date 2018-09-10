package org.ufpr.sistemapedidos.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


/**
 * Created by luancomputacao on 24/02/18.
 */
@Embeddable
public class ItemDoPedidoPK implements Serializable{
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private int idPedido;

    @Basic(optional = false)
    @Column(name = "id_produto")
    private int idProduto;

    public ItemDoPedidoPK(Integer pedidoId, Integer produtoId) {
        this.idPedido = pedidoId;
        this.idProduto = produtoId;
    }

    public ItemDoPedidoPK() {

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDoPedidoPK that = (ItemDoPedidoPK) o;
        return getIdPedido() == that.getIdPedido() &&
                getIdProduto() == that.getIdProduto();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdPedido(), getIdProduto());
    }

    @Override
    public String toString() {
        return "ItemDoPedidoPK{" +
                "idPedido=" + idPedido +
                ", idProduto=" + idProduto +
                '}';
    }
}
