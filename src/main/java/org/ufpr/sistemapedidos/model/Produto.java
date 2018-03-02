package org.ufpr.sistemapedidos.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by luancomputacao on 24/02/18.
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotBlank
    @Column(name = "id", length = 11)
    private Integer id;

    @Length(max = 45)
    @Column(name = "descricao", length = 45)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<ItemDoPedido> itemDoPedidoCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<ItemDoPedido> getItemDoPedidoCollection() {
        return itemDoPedidoCollection;
    }

    public void setItemDoPedidoCollection(Collection<ItemDoPedido> itemDoPedidoCollection) {
        this.itemDoPedidoCollection = itemDoPedidoCollection;
    }
}
