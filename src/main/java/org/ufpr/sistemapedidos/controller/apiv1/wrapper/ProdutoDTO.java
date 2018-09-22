package org.ufpr.sistemapedidos.controller.apiv1.wrapper;

/**
 *
 */
public class ProdutoDTO {
    private Integer id;
    private String descricao;

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

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", descricao=" + descricao +
                '}';
    }
}
