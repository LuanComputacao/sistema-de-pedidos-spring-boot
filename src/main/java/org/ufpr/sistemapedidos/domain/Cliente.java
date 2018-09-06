package org.ufpr.sistemapedidos.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by luancomputacao on 24/02/18.
 */
@Entity
@Table(name = "cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", length = 11)
    private Integer id;

    @Length(max = 11)
    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @Length(max = 30)
    @Column(name = "nome", length = 30)
    private String nome;

    @Length(max = 50)
    @Column(name = "sobrenome", length = 50)
    private String sobrenome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente" , fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<Pedido> pedidoCollection;

    public Cliente() { }

    public Cliente(String cpf, String nome, String sobrenome) {
        this.setCpf(cpf);
        this.setNome(nome);
        this.setSobrenome(sobrenome);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                '}';
    }
}
