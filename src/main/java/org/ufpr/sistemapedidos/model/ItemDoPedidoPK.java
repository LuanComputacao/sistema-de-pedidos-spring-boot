package org.ufpr.sistemapedidos.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;


/**
 * Created by luancomputacao on 24/02/18.
 */
@Embeddable
public class ItemDoPedidoPK {
    @Basic(optional = false)
    @NotBlank
    @Column(name = "id_pedido")
    private int idPedido;

    @Basic(optional = false)
    @NotBlank
    @Column(name = "id_produto")
    private int idProduto;

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
}
