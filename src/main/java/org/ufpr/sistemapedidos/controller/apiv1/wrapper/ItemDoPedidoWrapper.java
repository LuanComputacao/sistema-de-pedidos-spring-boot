package org.ufpr.sistemapedidos.controller.apiv1.wrapper;

public class ItemDoPedidoWrapper {
    private Integer produtoId;
    private Integer qtdade;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQtdade() {
        return qtdade;
    }

    public void setQtdade(Integer qtdade) {
        this.qtdade = qtdade;
    }

    @Override
    public String toString() {
        return "ItemDoPedidoWrapper{" +
                "produtoId=" + produtoId +
                ", qtdade=" + qtdade +
                '}';
    }
}
